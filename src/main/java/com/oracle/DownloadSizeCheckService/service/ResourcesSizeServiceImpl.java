package com.oracle.DownloadSizeCheckService.service;


import com.oracle.DownloadSizeCheckService.exception.NotFoundException;
import com.oracle.DownloadSizeCheckService.dto.ComplexResourcesDto;
import com.oracle.DownloadSizeCheckService.dto.SingleResourcesDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.pmw.tinylog.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class ResourcesSizeServiceImpl implements ResourcesSizeService {

    private final HttpHeaders requestHeaders = new HttpHeaders();

    @PostConstruct
    public void init() {
        requestHeaders.add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        requestHeaders.add("Accept-Encoding", "gzip, deflate, br");
        requestHeaders.add("Accept-Language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7");
        requestHeaders.add("Cache-Control", "max-age=0");
        requestHeaders.add("Connection", "keep-alive");
        requestHeaders.add("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36");
    }

    @Override
    public SingleResourcesDto getSingleResourcesSize(String path) {
        SingleResourcesDto dto = new SingleResourcesDto();
        dto.setSize(getResourcesSize(path));
        return dto;
    }

    @Override
    public ComplexResourcesDto getComplexResourcesSize(String path) {
        ComplexResourcesDto dto = new ComplexResourcesDto();
        dto.setRequestCount(1);
        dto.setTotalSize(getResourcesSize(path));

        Document document;
        try {
            document = Jsoup.connect(path).get();
        } catch (IOException e) {
            Logger.error(e.getMessage());
            return dto;
        }

        List<String> imageLinks = getImageLinks(document);
        setCountImages(dto, imageLinks);
        return dto;
    }

    private Long getResourcesSize(String path) {
        HttpEntity entity = new HttpEntity(requestHeaders);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> response = restTemplate.exchange(path, HttpMethod.HEAD, entity, String.class);

        HttpHeaders responseHeaders = response.getHeaders();
        List<String> contentLengthHeaders = responseHeaders.get("Content-Length");
        if (contentLengthHeaders == null) {
            Logger.error("Request does not contains Content-Length header!");
            throw new NotFoundException(path);
        } else {
            return Long.valueOf(contentLengthHeaders.get(0));
        }
    }

    private void setCountImages(ComplexResourcesDto dto, List<String> imageLinks) {
        int requestCount = dto.getRequestCount();
        long totalSize = dto.getTotalSize();
        Map<String, Long> images = new HashMap<>();
        for (String link : imageLinks) {
            if (images.containsKey(link)) {
                totalSize += images.get(link);
            } else {
                long size = getResourcesSize(link);
                totalSize += size;
                images.put(link, size);
                requestCount++;
            }
        }
        dto.setRequestCount(requestCount);
        dto.setTotalSize(totalSize);
        dto.setImages(images);
    }

    private List<String> getImageLinks(Document document) {
        return document.select("img")
                .stream().map(link -> link.attr("abs:src")).collect(Collectors.toList());
    }
}

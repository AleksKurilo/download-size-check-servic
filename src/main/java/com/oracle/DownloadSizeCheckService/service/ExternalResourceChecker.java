package com.oracle.DownloadSizeCheckService.service;

import com.oracle.DownloadSizeCheckService.dto.ExternalResourceDto;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ExternalResourceChecker {

    private final ResourceSizeChecker resourceSizeChecker;

    public ExternalResourceDto check(Document document) {
        List<String> imageLinks = document.select("img")
                .stream().map(link -> link.attr("abs:src")).collect(Collectors.toList());

        int requestCount = 0;
        int totalSize = 0;
        Map<String, Long> externalResources = new HashMap<>();

        for (String link : imageLinks) {
            if (externalResources.containsKey(link)) {
                totalSize += externalResources.get(link);
            } else {
                long size = resourceSizeChecker.check(link);
                totalSize += size;
                externalResources.put(link, size);
                requestCount++;
            }
        }

        ExternalResourceDto externalResourceDto = new ExternalResourceDto();
        externalResourceDto.setRequestCount(requestCount);
        externalResourceDto.setSize(totalSize);
        externalResourceDto.setExternalResources(externalResources);
        return externalResourceDto;
    }

}

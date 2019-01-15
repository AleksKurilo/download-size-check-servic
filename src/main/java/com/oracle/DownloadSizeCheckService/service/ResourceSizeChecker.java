package com.oracle.DownloadSizeCheckService.service;

import com.oracle.DownloadSizeCheckService.exception.HeaderValueNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class ResourceSizeChecker {

    static final HttpHeaders requestHeaders = new HttpHeaders();

    private final RestTemplate restTemplate;

    static {
        requestHeaders.add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        requestHeaders.add("Accept-Encoding", "gzip, deflate, br");
        requestHeaders.add("Accept-Language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7");
        requestHeaders.add("Cache-Control", "max-age=0");
        requestHeaders.add("Connection", "keep-alive");
        requestHeaders.add("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36");
    }

    long check(@NonNull String url) {
        HttpEntity entity = new HttpEntity(requestHeaders);
        HttpEntity<Void> response = restTemplate.exchange(url, HttpMethod.HEAD, entity, Void.class);
        HttpHeaders responseHeaders = response.getHeaders();
        long contentLength = responseHeaders.getContentLength();
        if (contentLength == -1) {
            throw new HeaderValueNotFoundException(url, "Content-Length");
        }
        return contentLength;
    }

}

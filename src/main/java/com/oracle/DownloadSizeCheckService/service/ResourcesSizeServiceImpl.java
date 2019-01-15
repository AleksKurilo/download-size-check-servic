package com.oracle.DownloadSizeCheckService.service;


import com.oracle.DownloadSizeCheckService.dto.ExternalResourceDto;
import com.oracle.DownloadSizeCheckService.dto.ResourcesSizeDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ResourcesSizeServiceImpl implements ResourcesSizeService {

    private final ExternalResourceChecker externalResourceChecker;

    @Override
    @SneakyThrows
    public ResourcesSizeDto getComplexResourcesSize(String url) {
        Document document = Jsoup.connect(url).get();
        long baseResourceSize = document.html().length();
        ExternalResourceDto externalResourceSize = externalResourceChecker.check(document);

        ResourcesSizeDto dto = new ResourcesSizeDto();
        dto.setRequestCount(1 + externalResourceSize.getRequestCount());
        dto.setExternalResource(externalResourceSize.getExternalResources());
        dto.setTotalSize(baseResourceSize + externalResourceSize.getSize());

        return dto;
    }
}

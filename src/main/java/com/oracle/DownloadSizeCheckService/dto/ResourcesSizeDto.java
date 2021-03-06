package com.oracle.DownloadSizeCheckService.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ResourcesSizeDto {

    private long totalSize;

    private int requestCount;

    private Map<String, Long> externalResource;
}

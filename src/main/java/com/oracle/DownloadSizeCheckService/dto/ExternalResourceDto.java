package com.oracle.DownloadSizeCheckService.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ExternalResourceDto {

    private int size;

    private int requestCount;

    private Map<String, Long> externalResources;
}

package com.oracle.DownloadSizeCheckService.service;

import com.oracle.DownloadSizeCheckService.dto.ResourcesSizeDto;

public interface ResourcesSizeService {

    ResourcesSizeDto getComplexResourcesSize(String url);
}

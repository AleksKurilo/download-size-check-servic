package com.oracle.DownloadSizeCheckService.service;

import com.oracle.DownloadSizeCheckService.dto.ComplexResourcesDto;

public interface ResourcesSizeService {

    ComplexResourcesDto getComplexResourcesSize(String path);
}

package com.oracle.DownloadSizeCheckService.service;

import com.oracle.DownloadSizeCheckService.dto.ComplexResourcesDto;
import com.oracle.DownloadSizeCheckService.dto.SingleResourcesDto;

public interface ResourcesSizeService {

    SingleResourcesDto getSingleResourcesSize(String path);

    ComplexResourcesDto getComplexResourcesSize(String path);
}

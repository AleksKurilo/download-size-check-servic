package com.oracle.DownloadSizeCheckService.controller;

import com.oracle.DownloadSizeCheckService.dto.ComplexResourcesDto;
import com.oracle.DownloadSizeCheckService.service.ResourcesSizeService;
import com.oracle.DownloadSizeCheckService.dto.SingleResourcesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/")
@RequiredArgsConstructor
public class ResourcesSizeController {

    private final ResourcesSizeService resourcesSizeService;

    @GetMapping(path = "/singleResources")
    public SingleResourcesDto getSingleResourcesSize(@RequestParam String path) {
        return resourcesSizeService.getSingleResourcesSize(path);
    }

    @GetMapping(path = "/complexResources")
    public ComplexResourcesDto getComplexResourcesSize(@RequestParam String path) {
        return resourcesSizeService.getComplexResourcesSize(path);
    }
}

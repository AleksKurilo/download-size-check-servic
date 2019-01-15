package com.oracle.DownloadSizeCheckService.controller;

import com.oracle.DownloadSizeCheckService.dto.ResourcesSizeDto;
import com.oracle.DownloadSizeCheckService.service.ResourcesSizeService;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/resources")
@RequiredArgsConstructor
public class ResourcesSizeController {

    private final ResourcesSizeService resourcesSizeService;

    @GetMapping(path = "/size")
    public ResourcesSizeDto getSingleResourcesSize(@URL @RequestParam String path) {
        return resourcesSizeService.getComplexResourcesSize(path);
    }

}

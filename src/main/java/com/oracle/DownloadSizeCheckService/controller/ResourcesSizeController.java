package com.oracle.DownloadSizeCheckService.controller;

import com.oracle.DownloadSizeCheckService.dto.ComplexResourcesDto;
import com.oracle.DownloadSizeCheckService.dto.SingleResourcesDto;
import com.oracle.DownloadSizeCheckService.service.ResourcesSizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping(path = "/")
@RequiredArgsConstructor
@Validated
public class ResourcesSizeController {

    private static final String VALIDATION_REGEX = "(http:/|https:/)?(/[^\\s\"'<>]+)+/?";
    private final ResourcesSizeService resourcesSizeService;

    @GetMapping(path = "/singleResources")
    public SingleResourcesDto getSingleResourcesSize(@Valid @Pattern(regexp = VALIDATION_REGEX) @RequestParam String path) {
        return resourcesSizeService.getSingleResourcesSize(path);
    }

    @GetMapping(path = "/complexResources")
    public ComplexResourcesDto getComplexResourcesSize(@Valid @Pattern(regexp = VALIDATION_REGEX) @RequestParam String path) {
        return resourcesSizeService.getComplexResourcesSize(path);
    }
}

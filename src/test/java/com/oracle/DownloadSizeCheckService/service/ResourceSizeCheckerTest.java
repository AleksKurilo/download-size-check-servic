package com.oracle.DownloadSizeCheckService.service;

import com.oracle.DownloadSizeCheckService.exception.HeaderValueNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ResourceSizeCheckerTest {

    private static final String TEST_URL = "https://test.com";
    private static final long TEST_URL_SIZE = 1000L;

    @InjectMocks
    private ResourceSizeChecker checker;

    @Mock
    private RestTemplate restTemplate;

    @Test(expected = NullPointerException.class)
    public void shouldThrowNPEIfUrlIsNull() {
        checker.check(null);
    }

    @Test
    public void shouldReturnResourceSize() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentLength(TEST_URL_SIZE);
        HttpEntity requestEntity = new HttpEntity(ResourceSizeChecker.requestHeaders);
        when(restTemplate.exchange(TEST_URL, HttpMethod.HEAD, requestEntity, Void.class)).thenReturn(new ResponseEntity<>(null, headers, HttpStatus.OK));

        long size = checker.check(TEST_URL);

        assertThat(size).isEqualTo(TEST_URL_SIZE);
        verify(restTemplate).exchange(TEST_URL, HttpMethod.HEAD, requestEntity, Void.class);
    }

    @Test(expected = HeaderValueNotFoundException.class)
    public void shouldReturnThrowResourceNotReachedExceptionIfResponseCodeIs400() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity requestEntity = new HttpEntity(ResourceSizeChecker.requestHeaders);
        when(restTemplate.exchange(TEST_URL, HttpMethod.HEAD, requestEntity, Void.class)).thenReturn(new ResponseEntity<>(null, headers, HttpStatus.BAD_REQUEST));

        checker.check(TEST_URL);
    }
}
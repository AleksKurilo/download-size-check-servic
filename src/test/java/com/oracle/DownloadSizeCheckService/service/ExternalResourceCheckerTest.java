package com.oracle.DownloadSizeCheckService.service;

import com.oracle.DownloadSizeCheckService.dto.ExternalResourceDto;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExternalResourceCheckerTest {

    private static final String TEST_URL = "https://test.com";
    private static final String TEST_IMAGE_URL = "https://image.jpg";
    private static final long TEST_URL_SIZE = 1000L;
    private static final String HTML = "<html> <img src=\"https://image.jpg\" > </html>";
    private static final String HTML_WITHOUT_EXTERNAL_RESOURCES = "<html> <img src=\"https://image.jpg\" > </html>";

    @InjectMocks
    private ExternalResourceChecker externalResourceChecker;

    @Mock
    private ResourceSizeChecker resourceSizeChecker;

    @Test
    public void shouldExternalReturnResourceSize() {
        Document document = new Document(TEST_URL).html(HTML).ownerDocument();
        when(resourceSizeChecker.check(TEST_IMAGE_URL)).thenReturn(TEST_URL_SIZE);
        ExternalResourceDto dto = externalResourceChecker.check(document);
        assertThat(dto.getSize()).isEqualTo(TEST_URL_SIZE);

    }

    @Test
    public void shouldExternalReturnOneRequestCount() {
        Document document = new Document(TEST_URL).html(HTML).ownerDocument();
        when(resourceSizeChecker.check(TEST_IMAGE_URL)).thenReturn(TEST_URL_SIZE);
        ExternalResourceDto dto = externalResourceChecker.check(document);
        assertThat(dto.getRequestCount()).isEqualTo(1);
    }

    @Test
    public void shouldExternalReturnExternalResourcesUrl() {
        Document document = new Document(TEST_URL).html(HTML).ownerDocument();
        when(resourceSizeChecker.check(TEST_IMAGE_URL)).thenReturn(TEST_URL_SIZE);
        ExternalResourceDto dto = externalResourceChecker.check(document);
        assertThat(dto.getExternalResources().containsKey(TEST_IMAGE_URL));
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNPEIfDocumentIsNull() {
        externalResourceChecker.check(null);

    }

    @Test
    public void shouldReturnEmptyExternalResourceSize() {
        Document document = new Document(TEST_URL).html(HTML_WITHOUT_EXTERNAL_RESOURCES).ownerDocument();
        when(resourceSizeChecker.check(TEST_IMAGE_URL)).thenReturn(0L);

        ExternalResourceDto dto = externalResourceChecker.check(document);

        assertThat(dto.getSize()).isEqualTo(0);
        assertThat(dto.getRequestCount()).isEqualTo(1);
        assertThat(dto.getExternalResources().isEmpty());
    }


}

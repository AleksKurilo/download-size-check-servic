package com.oracle.DownloadSizeCheckService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@AutoConfigureMockMvc

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResourcesSizeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final static String TEST_PAGE_VALID = "https://upload.wikimedia.org/wikipedia/commons/5/51/Google.png";
    private final static String TEST_PAGE_INVALID = "https://www.gearshout.net";

    @Test
    public void getSingleResourcesSizeTest()throws Exception{
        this.mockMvc.perform(get("/singleResources/")
                .param("path", TEST_PAGE_VALID)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("243397")));
    }

//    @Test
//    public void getSingleResourcesSizeNotFoundExceptionTest()throws Exception{
//        this.mockMvc.perform(get("/singleResources/")
//                .param("path", TEST_PAGE_INVALID)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().is4xxClientError());
//    }
//
//    @Test
//    public void getComplexResourcesSizeTest()throws Exception{
//        this.mockMvc.perform(get("/complexResources/")
//                .param("path", TEST_PAGE_VALID)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("243397")));
//    }
//
//    @Test
//    public void getComplexResourcesSizeNotFoundExceptionTest()throws Exception{
//        this.mockMvc.perform(get("/complexResources/")
//                .param("path", TEST_PAGE_INVALID)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().is4xxClientError());
//    }
}

package com.kawnayeen.logger.controller;

import com.kawnayeen.logger.AbstractControllerTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kawnayeen on 1/5/17.
 */
@Transactional
public class LoggingResourceTest extends AbstractControllerTest {
    @Before
    public void setUp() {
        super.setUp();
    }



    @Test
    public void testAuthWithValidCredential() throws Exception {

        String uri = "/auth";
        String authorizationData = generateBasicAuth("Anan","anan");
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(uri)
                .header("Authorization", authorizationData).accept(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcRestDocumentation.document("auth-valid-credential"));
    }

    @Test
    public void testAuthWithNoCredential() throws Exception{
        String uri = "/auth";
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(uri)
                .accept(MediaType.APPLICATION_JSON);
//        MvcResult result = mvc.perform(request).andReturn();
//        int status = result.getResponse().getStatus();
//        Assert.assertEquals("failure - expected HTTP status 401",401,status);

        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andDo(MockMvcRestDocumentation.document("auth-no-credential"));
    }

    @Test
    public void testAuthWithBadCredential() throws Exception{
        String uri = "/auth";
        String authorizationData = generateBasicAuth("Jata","jata");
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(uri)
                .header("Authorization", authorizationData).accept(MediaType.APPLICATION_JSON);
//        MvcResult result = mvc.perform(request).andReturn();
//        int status = result.getResponse().getStatus();
//        Assert.assertEquals("failure - expected HTTP status 401",401,status);
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andDo(MockMvcRestDocumentation.document("auth-invalid-cred"));
    }
}

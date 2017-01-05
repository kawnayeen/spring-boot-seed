package com.kawnayeen.logger.controller;

import com.google.common.net.HttpHeaders;
import com.kawnayeen.logger.AbstractControllerTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

/**
 * Created by kawnayeen on 1/5/17.
 */
@Transactional
public class LoggingResourceTest extends AbstractControllerTest{
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void testGetGreetings() throws Exception {

        String uri = "/auth";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uri).header("Authorization", "Basic QW5hbjphbmFu").accept(MediaType.APPLICATION_JSON)).andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println(content);
        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);

    }
}

package com.kawnayeen.logger.controller;

import com.kawnayeen.logger.AbstractControllerTest;
import com.kawnayeen.logger.model.LogLevel;
import com.kawnayeen.logger.model.housekeeping.DisplayName;
import com.kawnayeen.logger.model.housekeeping.LogInfo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kawnayeen on 1/5/17.
 */
@Transactional
public class LoggingResourceTest extends AbstractControllerTest {

    private static final String USERNAME = "Anan";
    private static final String PASSWORD = "anan";
    private static final String TOKEN = "eyJhbGciOiJIUzI1NiJ9" +
            ".eyJzdWIiOiJBbmFuIiwianRpIjoiMSIsImlzcyI6IkxvZ2dlckFwcGxpY2F0aW9uIiwiaWF0IjoxNDg0NTQzODc0LCJSb2xlcyI6Ilt7XCJpZFwiOjEsXCJjb2RlXCI6XCJST0xFX1VTRVJcIixcImxhYmVsXCI6XCJVU0VSXCJ9XSJ9" +
            ".Me41JSTRhkyx2xb0_NHW6URWwFNimCjyu-3-Gvs1C8E";
    private static final String AUTH = "/auth";
    private static final String REGISTER = "/register";
    private static final String LOG = "/log";

    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void testAuthWithValidCredential() throws Exception {
        String authorizationData = generateBasicAuth(USERNAME, PASSWORD);
        MockHttpServletRequestBuilder request = generatePostRequest(AUTH,authorizationData,"");
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(getDocument("auth-valid-credential"));
    }

    @Test
    public void testAuthWithNoCredential() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(AUTH)
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andDo(getDocument("auth-no-credential"));
    }

    @Test
    public void testAuthWithBadCredential() throws Exception {
        String authorizationData = generateBasicAuth("Jata", "jata");
        MockHttpServletRequestBuilder request = generatePostRequest(AUTH,authorizationData,"");
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andDo(getDocument("auth-invalid-cred"));
    }

    @Test
    public void testRegisterWithValidTokenAndRequestBody() throws Exception {
        String authorizationData = generateTokenAuth(TOKEN);
        DisplayName displayName = new DisplayName("Test Application");
        MockHttpServletRequestBuilder request;
        request = generatePostRequest(REGISTER, authorizationData, mapToJson(displayName));
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(getDocument("register-success"));
    }


    @Test
    public void testRegisterWithInValidToken() throws Exception {
        String authorizationData = generateTokenAuth("Invalid Token");
        DisplayName displayName = new DisplayName("Test Application");
        MockHttpServletRequestBuilder request;
        request = generatePostRequest(REGISTER, authorizationData, mapToJson(displayName));
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andDo(getDocument("register-invalid-token"));
    }


    @Test
    public void testRegisterWithInValidRequestBody() throws Exception {
        String authorizationData = generateTokenAuth(TOKEN);
        DisplayName displayName = new DisplayName("TestApplicationTestApplicationTestApplicationTestApplicationTestApplication");
        MockHttpServletRequestBuilder request;
        request = generatePostRequest(REGISTER, authorizationData, mapToJson(displayName));
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(getDocument("register-invalid-body"));
    }

    @Test
    public void testLogWithValidTokenAndRequestBody() throws Exception {
        String authorizationData = generateTokenAuth(TOKEN);
        LogInfo logInfo = getValidLogInfo();
        MockHttpServletRequestBuilder request;
        request = generatePostRequest(LOG, authorizationData, mapToJson(logInfo));
        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(getDocument("log-success"));
    }

    @Test
    public void testLogWithInvalidAppId() throws Exception {
        String authorizationData = generateTokenAuth(TOKEN);
        LogInfo logInfo = getLogInfoWithInvalidAppId();
        MockHttpServletRequestBuilder request;
        request = generatePostRequest(LOG, authorizationData, mapToJson(logInfo));
        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(getDocument("log-fail-invalid-app-id"));
    }

    @Test
    public void testLogWithContentLengthViolation() throws Exception {
        String authorizationData = generateTokenAuth(TOKEN);
        LogInfo logInfo = getLogInfoWithContentLengthViolation();
        MockHttpServletRequestBuilder request;
        request = generatePostRequest(LOG, authorizationData, mapToJson(logInfo));
        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(getDocument("log-fail-invalid-content-length"));
    }



    private LogInfo getValidLogInfo() {
        LogInfo logInfo = new LogInfo();
        logInfo.setApplicationId("test_app_id");
        logInfo.setLogger("com.cefalo.learning");
        logInfo.setLogLevel(LogLevel.WARN);
        logInfo.setMessage("Failed to validate address from google");
        return logInfo;
    }

    private LogInfo getLogInfoWithInvalidAppId(){
        LogInfo logInfo = new LogInfo();
        logInfo.setApplicationId("invalid_app_id");
        logInfo.setLogger("com.cefalo.learning");
        logInfo.setLogLevel(LogLevel.WARN);
        logInfo.setMessage("Failed to validate address from google");
        return logInfo;
    }

    private LogInfo getLogInfoWithContentLengthViolation(){
        LogInfo logInfo = new LogInfo();
        logInfo.setApplicationId("test_app_id");
        logInfo.setLogger("com.cefalo.learning.com.cefalo.learning.com.cefalo.learning.com." +
                "cefalo.learning.com.cefalo.learning.com.cefalo.learning.com.cefalo.learning." +
                "com.cefalo.learning.com.cefalo.learning.com.cefalo.learning.com.cefalo.learning." +
                "com.cefalo.learning.com.cefalo.learning.com.cefalo.learning.com.cefalo.learning.");
        logInfo.setLogLevel(LogLevel.WARN);
        logInfo.setMessage("Failed to validate address from google");
        return logInfo;
    }
}

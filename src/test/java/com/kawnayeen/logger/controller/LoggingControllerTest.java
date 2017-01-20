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
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by kawnayeen on 1/5/17.
 * Tests for {@link LoggingController}
 */
@Transactional
public class LoggingControllerTest extends AbstractControllerTest {

    private static final String USERNAME = "Anan";
    private static final String PASSWORD = "anan";
    private static final String TOKEN = "eyJhbGciOiJIUzI1NiJ9" +
            ".eyJzdWIiOiJBbmFuIiwianRpIjoiMSIsImlzcyI6IkxvZ2dlckFwcGxpY2F0aW9uIiwiaWF0IjoxNDg0NTQzODc0LCJSb2xlcyI6Ilt7XCJpZFwiOjEsXCJjb2RlXCI6XCJST0xFX1VTRVJcIixcImxhYmVsXCI6XCJVU0VSXCJ9XSJ9" +
            ".Me41JSTRhkyx2xb0_NHW6URWwFNimCjyu-3-Gvs1C8E";
    private static final String TEST_APP_ID = "test_app_id";


    private static final String AUTH = "/auth";
    private static final String REGISTER = "/applications";
    private static final String LOG = "/logs";
    private static final String PROFILE = "/profile";

    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void testAuthWithValidCredential() throws Exception {
        String authorizationData = generateBasicAuth(USERNAME, PASSWORD);
        MockHttpServletRequestBuilder request = generatePostRequest(AUTH, authorizationData, "");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andDo(getDocument("auth-valid-credential"));
    }

    @Test
    public void testAuthWithNoCredential() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(AUTH)
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(status().isUnauthorized())
                .andDo(getDocument("auth-no-credential"));
    }

    @Test
    public void testAuthWithBadCredential() throws Exception {
        String authorizationData = generateBasicAuth("Jata", "jata");
        MockHttpServletRequestBuilder request = generatePostRequest(AUTH, authorizationData, "");
        mvc.perform(request)
                .andExpect(status().isUnauthorized())
                .andDo(getDocument("auth-invalid-cred"));
    }

    @Test
    public void testAddApplicationWithValidTokenAndRequestBody() throws Exception {
        String authorizationData = generateTokenAuth(TOKEN);
        DisplayName displayName = new DisplayName("Test Application");
        MockHttpServletRequestBuilder request;
        request = generatePostRequest(REGISTER, authorizationData, mapToJson(displayName));
        mvc.perform(request)
                .andExpect(status().isCreated())
                .andDo(getDocument("register-success"));
    }


    @Test
    public void testAddApplicationWithInValidToken() throws Exception {
        String authorizationData = generateTokenAuth("Invalid Token");
        DisplayName displayName = new DisplayName("Test Application");
        MockHttpServletRequestBuilder request;
        request = generatePostRequest(REGISTER, authorizationData, mapToJson(displayName));
        mvc.perform(request)
                .andExpect(status().isForbidden())
                .andDo(getDocument("register-invalid-token"));
    }


    @Test
    public void testAddApplicationWithInValidRequestBody() throws Exception {
        String authorizationData = generateTokenAuth(TOKEN);
        DisplayName displayName = new DisplayName("TestApplicationTestApplicationTestApplicationTestApplicationTestApplication");
        MockHttpServletRequestBuilder request;
        request = generatePostRequest(REGISTER, authorizationData, mapToJson(displayName));
        mvc.perform(request)
                .andExpect(status().isBadRequest())
                .andDo(getDocument("register-invalid-body"));
    }

    @Test
    public void testGetApplicationWithValidTokenAndAppId() throws Exception {
        String authorizationData = generateTokenAuth(TOKEN);
        String uri = REGISTER + "/" + TEST_APP_ID;
        MockHttpServletRequestBuilder request;
        request = generateGetRequest(uri, authorizationData);
        mvc.perform(request).andExpect(status().isOk())
                .andDo(getDocument("get-log-success"));
    }

    @Test
    public void testAddLogWithValidTokenAndRequestBody() throws Exception {
        String authorizationData = generateTokenAuth(TOKEN);
        LogInfo logInfo = getValidLogInfo();
        MockHttpServletRequestBuilder request;
        request = generatePostRequest(LOG, authorizationData, mapToJson(logInfo));
        mvc.perform(request).andExpect(status().isCreated())
                .andDo(getDocument("log-success"));
    }

    @Test
    public void testAddLogWithInvalidAppId() throws Exception {
        String authorizationData = generateTokenAuth(TOKEN);
        LogInfo logInfo = getLogInfoWithInvalidAppId();
        MockHttpServletRequestBuilder request;
        request = generatePostRequest(LOG, authorizationData, mapToJson(logInfo));
        mvc.perform(request).andExpect(status().isBadRequest())
                .andDo(getDocument("log-fail-invalid-app-id"));
    }

    @Test
    public void testAddLogWithContentLengthViolation() throws Exception {
        String authorizationData = generateTokenAuth(TOKEN);
        LogInfo logInfo = getLogInfoWithContentLengthViolation();
        MockHttpServletRequestBuilder request;
        request = generatePostRequest(LOG, authorizationData, mapToJson(logInfo));
        mvc.perform(request).andExpect(status().isBadRequest())
                .andDo(getDocument("log-fail-invalid-content-length"));
    }

    @Test
    public void testGetLogWithValidLogId() throws Exception {
        String authorizationData = generateTokenAuth(TOKEN);
        String uri = LOG + "/1";
        MockHttpServletRequestBuilder request;
        request = generateGetRequest(uri, authorizationData);
        mvc.perform(request).andExpect(status().isOk());
    }

    @Test
    public void testGetLogWithInvalidLogId() throws Exception {
        String authorizationData = generateTokenAuth(TOKEN);
        String uri = LOG + "/" + Long.MAX_VALUE;
        MockHttpServletRequestBuilder request = generateGetRequest(uri, authorizationData);
        mvc.perform(request).andExpect(status().isBadRequest());
    }

    public void testGetProfile() throws Exception{
        String authorizationData = generateTokenAuth(TOKEN);
        MockHttpServletRequestBuilder request = generateGetRequest(PROFILE,authorizationData);
        mvc.perform(request).andExpect(status().isOk());
    }

    private LogInfo getValidLogInfo() {
        LogInfo logInfo = new LogInfo();
        logInfo.setApplicationId(TEST_APP_ID);
        logInfo.setLogger("com.cefalo.learning");
        logInfo.setLogLevel(LogLevel.WARN);
        logInfo.setMessage("Failed to validate address from google");
        return logInfo;
    }

    private LogInfo getLogInfoWithInvalidAppId() {
        LogInfo logInfo = new LogInfo();
        logInfo.setApplicationId("invalid_app_id");
        logInfo.setLogger("com.cefalo.learning");
        logInfo.setLogLevel(LogLevel.WARN);
        logInfo.setMessage("Failed to validate address from google");
        return logInfo;
    }

    private LogInfo getLogInfoWithContentLengthViolation() {
        LogInfo logInfo = new LogInfo();
        logInfo.setApplicationId(TEST_APP_ID);
        logInfo.setLogger("com.cefalo.learning.com.cefalo.learning.com.cefalo.learning.com." +
                "cefalo.learning.com.cefalo.learning.com.cefalo.learning.com.cefalo.learning." +
                "com.cefalo.learning.com.cefalo.learning.com.cefalo.learning.com.cefalo.learning." +
                "com.cefalo.learning.com.cefalo.learning.com.cefalo.learning.com.cefalo.learning.");
        logInfo.setLogLevel(LogLevel.WARN);
        logInfo.setMessage("Failed to validate address from google");
        return logInfo;
    }
}
package com.kawnayeen.logger.controller;

import com.kawnayeen.logger.hateoas.ApplicationResource;
import com.kawnayeen.logger.hateoas.AuthSuccessResource;
import com.kawnayeen.logger.hateoas.LogResource;
import com.kawnayeen.logger.misc.StringUtility;
import com.kawnayeen.logger.model.LoggerUser;
import com.kawnayeen.logger.model.entity.Account;
import com.kawnayeen.logger.model.entity.Application;
import com.kawnayeen.logger.model.entity.Log;
import com.kawnayeen.logger.model.housekeeping.DisplayName;
import com.kawnayeen.logger.model.housekeeping.LogInfo;
import com.kawnayeen.logger.model.service.AccountService;
import com.kawnayeen.logger.model.service.ApplicationService;
import com.kawnayeen.logger.model.service.LogService;
import com.kawnayeen.logger.security.annotation.BasicAuthentication;
import com.kawnayeen.logger.security.annotation.CurrentUser;
import com.kawnayeen.logger.security.annotation.RateLimit;
import com.kawnayeen.logger.security.annotation.TokenAuthentication;
import com.kawnayeen.logger.security.token.auth.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

/**
 * Created by kawnayeen on 1/2/17.
 */
@RestController
public class LoggingController {

    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    StringUtility stringUtility;
    @Autowired
    AccountService accountService;
    @Autowired
    ApplicationService applicationService;
    @Autowired
    LogService logService;

    @BasicAuthentication
    @RequestMapping(
            value = "/auth",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AuthSuccessResource> auth(@CurrentUser LoggerUser loggerUser) {
        String accessToken = jwtUtil.generateToken(loggerUser);
        return new ResponseEntity<>(new AuthSuccessResource(accessToken), HttpStatus.OK);
    }

    @TokenAuthentication
    @RequestMapping(
            value = "/register",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApplicationResource> createNewApplication(@CurrentUser LoggerUser loggerUser, @RequestBody DisplayName displayName) {
        Account account = accountService.findOne(loggerUser.getId());
        Application application = new Application();
        application.setDisplayName(displayName.getDisplayName());
        application.setApplicationId(stringUtility.randomString());
        application.setApplicationSecret(stringUtility.randomString());
        application.setAccount(account);
        ApplicationResource applicationResource = new ApplicationResource(applicationService.create(application));
        return new ResponseEntity<>(applicationResource, HttpStatus.CREATED);
    }

    @TokenAuthentication
    @RequestMapping(
            value = "/application/{appId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApplicationResource> getApplication(@CurrentUser LoggerUser loggerUser, @PathVariable("appId") String appId) {
        ApplicationResource applicationResource = new ApplicationResource(applicationService.findByApplicationId(appId));
        return new ResponseEntity<ApplicationResource>(applicationResource, HttpStatus.OK);
    }

    @TokenAuthentication
    @RequestMapping(
            value = "logs/{logId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<LogResource> getLog(@CurrentUser LoggerUser loggerUser, @PathVariable("logId") Long logId) {
        LogResource logResource = new LogResource(logService.findOne(logId));
        return new ResponseEntity<>(logResource, HttpStatus.OK);
    }

    @RateLimit(value = 3)
    @TokenAuthentication
    @RequestMapping(
            value = "/log",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Map<String, Object>> addNewLog(@CurrentUser LoggerUser loggerUser, @RequestBody LogInfo logInfo) {
        Log log = new Log();
        log.setApplication(applicationService.findByApplicationId(logInfo.getApplicationId()));
        log.setLogger(logInfo.getLogger());
        log.setLevel(logInfo.getLogLevel());
        log.setMessage(logInfo.getMessage());
        logService.create(log);
        return new ResponseEntity<>(Collections.singletonMap("success", true), HttpStatus.CREATED);
    }

    @TokenAuthentication
    @RequestMapping(
            value = "/profile",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Account> getAccountDetails(@CurrentUser LoggerUser loggerUser) {
        return new ResponseEntity<>(accountService.findOne(loggerUser.getId()), HttpStatus.OK);
    }
}
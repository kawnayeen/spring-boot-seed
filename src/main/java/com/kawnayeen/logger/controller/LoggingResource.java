package com.kawnayeen.logger.controller;

import com.kawnayeen.logger.model.LoggerUser;
import com.kawnayeen.logger.model.entity.Application;
import com.kawnayeen.logger.model.housekeeping.DisplayName;
import com.kawnayeen.logger.model.housekeeping.LogInfo;
import com.kawnayeen.logger.security.annotation.BasicAuthentication;
import com.kawnayeen.logger.security.annotation.CurrentUser;
import com.kawnayeen.logger.security.annotation.TokenAuthentication;
import com.kawnayeen.logger.security.token.auth.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * Created by kawnayeen on 1/2/17.
 */
@RestController
public class LoggingResource {

    @Autowired
    JwtUtil jwtUtil;

    @BasicAuthentication
    @RequestMapping(
            value = "/auth",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Map<String,String>> helloWorld(@CurrentUser LoggerUser loggerUser) {
        String accessToken = jwtUtil.generateToken(loggerUser);
        return new ResponseEntity<>(Collections.singletonMap("accessToken",accessToken), HttpStatus.OK);
    }

    @TokenAuthentication
    @RequestMapping(
            value = "/register",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Application> createNewApplication(@CurrentUser LoggerUser loggerUser, @RequestBody DisplayName displayName) {
        System.out.println(displayName.toString());
        return new ResponseEntity<Application>(HttpStatus.CREATED);
    }

    @TokenAuthentication
    @RequestMapping(
            value = "/log",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Map<String, Boolean>> addNewLog(@CurrentUser LoggerUser loggerUser, @RequestBody LogInfo logInfo) {
        System.out.println(logInfo.toString());
        return new ResponseEntity<>(Collections.singletonMap("success",false),HttpStatus.OK);
    }
}

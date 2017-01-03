package com.kawnayeen.logger.controller;

import com.kawnayeen.logger.model.LoggerUser;
import com.kawnayeen.logger.security.token.auth.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by kawnayeen on 1/2/17.
 */
@RestController
public class HelloWorld {

    @Autowired
    JwtUtil jwtUtil;

    @RequestMapping(value = "/api/hello", method = RequestMethod.GET)
    public String helloWorld(Principal principal){
        LoggerUser loggerUser = (LoggerUser) ((Authentication)principal).getPrincipal();
        return "Success: true \nToken : "+jwtUtil.generateToken(loggerUser);
    }
}

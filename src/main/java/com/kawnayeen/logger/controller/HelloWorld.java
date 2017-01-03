package com.kawnayeen.logger.controller;

import com.kawnayeen.logger.model.LoggerUser;
import com.kawnayeen.logger.security.annotation.BasicAuthentication;
import com.kawnayeen.logger.security.annotation.CurrentUser;
import com.kawnayeen.logger.security.token.auth.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kawnayeen on 1/2/17.
 */
@RestController
public class HelloWorld {

    @Autowired
    JwtUtil jwtUtil;

    @BasicAuthentication
    @RequestMapping(value = "/api/hello", method = RequestMethod.GET)
    public String helloWorld(@CurrentUser LoggerUser loggerUser){
        return "Success: true \nToken : "+jwtUtil.generateToken(loggerUser);
    }
}

package com.kawnayeen.logger.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kawnayeen on 2/6/17.
 */
@RestController
public class Welcome {
    @RequestMapping(value = "/")
    public String sayHello(){
        return "Welcome to Logger Application";
    }
}

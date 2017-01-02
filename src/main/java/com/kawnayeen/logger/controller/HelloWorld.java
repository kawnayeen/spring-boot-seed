package com.kawnayeen.logger.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kawnayeen on 1/2/17.
 */
@RestController
public class HelloWorld {

    @RequestMapping(value = "/api/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String helloWorld(){
        return "Hello World";
    }
}

package com.kawnayeen.logger.model.service.exception;

/**
 * Created by kawnayeen on 1/20/17.
 */
public class LogNotFoundException extends RuntimeException {
    public LogNotFoundException(){
        super("Invalid log id");
    }
}

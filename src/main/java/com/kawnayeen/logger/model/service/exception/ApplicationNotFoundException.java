package com.kawnayeen.logger.model.service.exception;

/**
 * Created by kawnayeen on 1/13/17.
 */
public class ApplicationNotFoundException extends RuntimeException{

    public ApplicationNotFoundException(){
        super("Invalid application id");
    }
}

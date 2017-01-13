package com.kawnayeen.logger.model.service.exception;

/**
 * Created by kawnayeen on 1/13/17.
 */
public class InvalidApplicationException extends RuntimeException{
    public InvalidApplicationException(){
        super("application entity constrain violated");
    }
    public InvalidApplicationException(String message){
        super(message);
    }
}

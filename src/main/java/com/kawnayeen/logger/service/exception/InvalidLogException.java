package com.kawnayeen.logger.service.exception;

/**
 * Created by kawnayeen on 1/13/17.
 */
public class InvalidLogException extends RuntimeException{
    public InvalidLogException(){
        super("length constrain violated");
    }

    public InvalidLogException(String message){
        super(message);
    }
}

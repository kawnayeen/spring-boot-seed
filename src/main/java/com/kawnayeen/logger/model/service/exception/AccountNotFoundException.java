package com.kawnayeen.logger.model.service.exception;

/**
 * Created by kawnayeen on 1/20/17.
 */
public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(){
        super("Account Not Found");
    }
}

package com.kawnayeen.logger.security.annotation;

import com.kawnayeen.logger.model.LoggerUser;

import java.lang.annotation.*;

/**
 * Created by kawnayeen on 1/12/17.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimit {
    int value();
}

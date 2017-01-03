package com.kawnayeen.logger.security.annotation;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

/**
 * Created by kawnayeen on 1/3/17.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@PreAuthorize("#loggerUser.isTokenAuthenticated()==false")
public @interface BasicAuthentication {
}

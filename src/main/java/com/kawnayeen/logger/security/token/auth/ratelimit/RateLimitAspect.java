package com.kawnayeen.logger.security.token.auth.ratelimit;

import com.kawnayeen.logger.model.LoggerUser;
import com.kawnayeen.logger.security.annotation.RateLimit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by kawnayeen on 1/12/17.
 */
@Aspect
@Component
public class RateLimitAspect {

    @Autowired
    private RateLimiter rateLimiter;

    @Around("@annotation(rateLimit) && args(loggerUser,..)")
    public Object checkRateLimitAnnotation(ProceedingJoinPoint proceedingJoinPoint, RateLimit rateLimit, LoggerUser loggerUser) throws Throwable {
        if (rateLimiter.willProceed(loggerUser.getAccessToken(), rateLimit.value())) {
            return proceedingJoinPoint.proceed();
        } else {
            return new ResponseEntity(Collections.singletonMap("error", "rate limit exceeded"), HttpStatus.FORBIDDEN);
        }

    }
}


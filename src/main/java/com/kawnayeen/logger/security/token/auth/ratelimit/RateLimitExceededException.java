package com.kawnayeen.logger.security.token.auth.ratelimit;

/**
 * Created by kawnayeen on 1/19/17.
 */
public class RateLimitExceededException extends RuntimeException {
    public RateLimitExceededException(){
        super("Rate limit exceeded");
    }
}

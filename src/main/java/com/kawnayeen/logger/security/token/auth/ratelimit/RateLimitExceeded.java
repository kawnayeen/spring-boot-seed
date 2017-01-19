package com.kawnayeen.logger.security.token.auth.ratelimit;

/**
 * Created by kawnayeen on 1/19/17.
 */
public class RateLimitExceeded extends Exception {
    public RateLimitExceeded(){
        super("Rate limit exceeded");
    }
}

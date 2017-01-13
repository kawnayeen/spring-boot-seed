package com.kawnayeen.logger.security.token.auth.ratelimit;

/**
 * Created by kawnayeen on 1/4/17.
 */
public interface RateLimiter {
    boolean willProceed(String token,int ratePerMinute);
}

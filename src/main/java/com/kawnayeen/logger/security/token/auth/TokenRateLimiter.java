package com.kawnayeen.logger.security.token.auth;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by kawnayeen on 1/4/17.
 */
@Service
public class TokenRateLimiter {
    private HashMap<String, RateLimiter> allRateLimiters = new HashMap<>();

    public RateLimiter getRateLimiter(String token){
        if(allRateLimiters.containsKey(token))
            return allRateLimiters.get(token);
        RateLimiter rateLimiter = RateLimiter.create(3);
        allRateLimiters.put(token,rateLimiter);
        return rateLimiter;
    }
}

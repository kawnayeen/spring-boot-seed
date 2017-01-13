package com.kawnayeen.logger.security.token.auth.ratelimit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by kawnayeen on 1/4/17.
 */
@Service
public class RateLimiterBean implements RateLimiter {

    StringRedisTemplate redisTemplate;
    ValueOperations<String,String> valueOps;

    @Autowired
    public RateLimiterBean(StringRedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
        valueOps = this.redisTemplate.opsForValue();
    }

    @Override
    public boolean willProceed(String token, int ratePerMinute) {
        Calendar calendar = Calendar.getInstance();
        int currentMinutes = calendar.get(Calendar.HOUR_OF_DAY) * 60 + calendar.get(Calendar.MINUTE);
        String key = currentMinutes+token;
        if(redisTemplate.hasKey(key)){
            valueOps.increment(key,1);
            int numberOfRequest = Integer.parseInt(valueOps.get(key));
            if(numberOfRequest<=ratePerMinute)
                return true;
            else
                return false;
        }else{
            valueOps.increment(key,1);
            redisTemplate.expire(key,3, TimeUnit.MINUTES);
            return true;
        }
    }
}

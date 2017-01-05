package com.kawnayeen.logger.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by kawnayeen on 1/5/17.
 */
@Service
public class StringUtility {
    public String randomString() {
        String randomStr = UUID.randomUUID().toString();
        randomStr = randomStr.replace("-", "");
        return randomStr;
    }
}

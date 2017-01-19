package com.kawnayeen.logger.model.service;

import com.kawnayeen.logger.model.entity.Log;

/**
 * Created by kawnayeen on 1/5/17.
 */
public interface LogService {
    Log create(Log log);
    Log findOne(Long id);
}

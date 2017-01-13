package com.kawnayeen.logger.model.service;

import com.kawnayeen.logger.model.entity.Application;

/**
 * Created by kawnayeen on 1/5/17.
 */
public interface ApplicationService {
    Application create(Application application);
    Application findByApplicationId(String applicationId);
}

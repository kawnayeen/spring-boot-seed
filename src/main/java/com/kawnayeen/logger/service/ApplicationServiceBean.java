package com.kawnayeen.logger.service;

import com.kawnayeen.logger.model.entity.Application;
import com.kawnayeen.logger.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kawnayeen on 1/5/17.
 */
@Service
public class ApplicationServiceBean implements ApplicationService {

    @Autowired
    ApplicationRepository applicationRepository;

    @Override
    public Application create(Application application) {
        if(application.getId()!=null)
            return null;
        return applicationRepository.save(application);
    }

    @Override
    public Application findByApplicationId(String applicationId) {
        return applicationRepository.findByApplicationId(applicationId);
    }
}

package com.kawnayeen.logger.service;

import com.kawnayeen.logger.model.entity.Application;
import com.kawnayeen.logger.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kawnayeen on 1/5/17.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ApplicationServiceBean implements ApplicationService {

    @Autowired
    ApplicationRepository applicationRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Application create(Application application) {
        if(application.getId()!=null)
            return null;
        return applicationRepository.save(application);
    }

    @Override
    @Cacheable(value = "application",key = "#applicationId")
    public Application findByApplicationId(String applicationId) {
        return applicationRepository.findByApplicationId(applicationId);
    }
}

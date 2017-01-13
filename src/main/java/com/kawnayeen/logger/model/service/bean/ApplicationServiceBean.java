package com.kawnayeen.logger.model.service.bean;

import com.kawnayeen.logger.model.entity.Application;
import com.kawnayeen.logger.model.service.ApplicationService;
import com.kawnayeen.logger.repository.ApplicationRepository;
import com.kawnayeen.logger.model.service.exception.ApplicationNotFoundException;
import com.kawnayeen.logger.model.service.exception.InvalidApplicationException;
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
            throw new InvalidApplicationException("Can not preset application id");
        try {
            return applicationRepository.save(application);
        }catch (Exception e){
            throw new InvalidApplicationException();
        }
    }

    @Override
    @Cacheable(value = "application",key = "#applicationId")
    public Application findByApplicationId(String applicationId) {
        Application application = applicationRepository.findByApplicationId(applicationId);
        if(application==null)
            throw new ApplicationNotFoundException();
        return application;
    }
}

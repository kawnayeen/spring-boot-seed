package com.kawnayeen.logger.service;

import com.kawnayeen.logger.AbstractTest;
import com.kawnayeen.logger.model.entity.Application;
import com.kawnayeen.logger.model.service.ApplicationService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kawnayeen on 1/5/17.
 */
@Transactional
public class ApplicationServiceTest extends AbstractTest{
    @Autowired
    private ApplicationService applicationService;

    @Test
    public void TestCreateApplication(){
        Application application = new Application();
        application.setApplicationId("applicationId");
        application.setApplicationSecret("applicationSecret");
        application.setDisplayName("Awesome Application");
        Application createdApplication = applicationService.create(application);
        Assert.assertNotNull(createdApplication);
        Assert.assertNotNull(createdApplication.getId());
        Assert.assertEquals(application.getApplicationId(),createdApplication.getApplicationId());
    }

    @Test
    public void TestCreatingApplicationWithId(){
        Application application = new Application();
        application.setId(1L);
        application.setApplicationId("applicationId");
        application.setApplicationSecret("applicationSecret");
        application.setDisplayName("Awesome Application");
        Application createdApplication = applicationService.create(application);
        Assert.assertNull(createdApplication);
    }
}

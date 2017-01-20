package com.kawnayeen.logger.model.services;

import com.kawnayeen.logger.AbstractTest;
import com.kawnayeen.logger.model.entity.Application;
import com.kawnayeen.logger.model.service.ApplicationService;
import com.kawnayeen.logger.model.service.exception.ApplicationNotFoundException;
import com.kawnayeen.logger.model.service.exception.InvalidApplicationException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by kawnayeen on 1/5/17.
 * Tests for {@link com.kawnayeen.logger.model.service.bean.AccountServiceBean}
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
        assertNotNull(createdApplication);
        assertNotNull(createdApplication.getId());
        assertEquals(application.getApplicationId(),createdApplication.getApplicationId());
    }

    @Test
    public void TestCreatingApplicationWithId() throws Exception{
        Application application = new Application();
        application.setId(1L);
        application.setApplicationId("applicationId");
        application.setApplicationSecret("applicationSecret");
        application.setDisplayName("Awesome Application");
        this.thrown.expect(InvalidApplicationException.class);
        applicationService.create(application);
    }

    @Test
    public void TestGetApplicationWithApplicationId()throws Exception{
        Application application = applicationService.findByApplicationId("test_app_id");
        assertNotNull(application);
    }

    @Test
    public void TestGetApplicationWithInvalidAppId() throws Exception{
        this.thrown.expect(ApplicationNotFoundException.class);
        applicationService.findByApplicationId("invalid_id");
    }
}

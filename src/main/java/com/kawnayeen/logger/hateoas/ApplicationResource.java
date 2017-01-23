package com.kawnayeen.logger.hateoas;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.kawnayeen.logger.controller.LoggingController;
import com.kawnayeen.logger.model.entity.Application;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by kawnayeen on 1/18/17.
 */
public class ApplicationResource extends ResourceSupport {

    private final String applicationId;
    private final String applicationSecret;
    private final String displayName;
    private final List<LogResource> logResources;

    @JsonCreator
    public ApplicationResource(Application application) {
        this.applicationId = application.getApplicationId();
        this.applicationSecret = application.getApplicationSecret();
        this.displayName = application.getDisplayName();
        this.logResources = application.getLogs()
                .stream()
                .map(LogResource::new)
                .collect(Collectors.toList());

        add(linkTo(methodOn(LoggingController.class)
                .getApplication(null, application.getApplicationId()))
                .withSelfRel());

        add(linkTo(methodOn(LoggingController.class)
                .addNewLog(null, null)).withRel("add_log"));
    }

    public String getApplicationId() {
        return applicationId;
    }

    public String getApplicationSecret() {
        return applicationSecret;
    }

    public String getDisplayName() {
        return displayName;
    }

    public List<LogResource> getLogResources() {
        return logResources;
    }
}

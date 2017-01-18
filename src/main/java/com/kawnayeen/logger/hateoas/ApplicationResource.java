package com.kawnayeen.logger.hateoas;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.kawnayeen.logger.model.entity.Application;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by kawnayeen on 1/18/17.
 */
public class ApplicationResource extends ResourceSupport {

    private final Application application;

    @JsonCreator
    public ApplicationResource(Application application) {
        this.application = application;
        add(new Link("/application/"+application.getApplicationId()).withSelfRel());
    }

    public Application getApplication() {
        return application;
    }
}

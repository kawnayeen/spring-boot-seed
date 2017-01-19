package com.kawnayeen.logger.hateoas;

import com.kawnayeen.logger.controller.LoggingController;
import com.kawnayeen.logger.model.entity.Log;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by kawnayeen on 1/19/17.
 */
public class LogResource extends ResourceSupport{
    private final Log log;

    public LogResource(Log log) {
        this.log = log;
        add(linkTo(methodOn(LoggingController.class)
                .getLog(null, log.getId()))
                .withSelfRel());
    }

    public Log getLog() {
        return log;
    }
}

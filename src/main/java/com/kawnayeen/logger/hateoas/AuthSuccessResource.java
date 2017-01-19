package com.kawnayeen.logger.hateoas;

import com.kawnayeen.logger.controller.LoggingController;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by kawnayeen on 1/19/17.
 */
public class AuthSuccessResource extends ResourceSupport{
    private final String accessToken;

    public AuthSuccessResource(String accessToken) {
        this.accessToken = accessToken;
        add(linkTo(methodOn(LoggingController.class)
                .createNewApplication(null, null))
                .withRel("add_application"));
        add(linkTo(methodOn(LoggingController.class).getAccountDetails(null))
                .withRel("profile"));
    }

    public String getAccessToken() {
        return accessToken;
    }
}

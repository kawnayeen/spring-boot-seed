package com.kawnayeen.logger.hateoas;

import com.kawnayeen.logger.controller.LoggingController;
import com.kawnayeen.logger.model.entity.Account;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by kawnayeen on 1/19/17.
 */
public class AccountResource extends ResourceSupport {
    private final String username;
    private final List<ApplicationResource> applicationList;

    public AccountResource(Account account) {
        this.username = account.getUsername();
        this.applicationList = account.getApplications()
                .stream()
                .map(ApplicationResource::new).collect(Collectors.toList());
        add(linkTo(methodOn(LoggingController.class)
                .getAccountDetails(null))
                .withSelfRel());
    }

    public String getUsername() {
        return username;
    }

    public List<ApplicationResource> getApplicationList() {
        return applicationList;
    }
}

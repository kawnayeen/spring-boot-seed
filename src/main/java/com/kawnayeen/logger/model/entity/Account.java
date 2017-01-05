package com.kawnayeen.logger.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by kawnayeen on 1/2/17.
 */
@Entity
public class Account {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String username;
    @JsonIgnore
    @NotNull
    private String password;
    @JsonIgnore
    @NotNull
    private boolean enabled = true;

    @JsonIgnore
    @NotNull
    private boolean credentialExpired = false;

    @JsonIgnore
    @NotNull
    private boolean expired = false;

    @JsonIgnore
    @NotNull
    private boolean locked = false;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "AccountRole",
            joinColumns = @JoinColumn(name = "accountId",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "id")
    )
    private Collection<Role> roles = new ArrayList<>();


    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private Collection<Application> applications = new ArrayList<>();

    public Account() {
    }

    public Account(Account account){
        id = account.getId();
        username = account.getUsername();
        password = account.getPassword();
        enabled = account.isEnabled();
        credentialExpired = account.isCredentialExpired();
        expired = account.isExpired();
        locked = account.isLocked();
        roles = account.getRoles();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isCredentialExpired() {
        return credentialExpired;
    }

    public void setCredentialExpired(boolean credentialExpired) {
        this.credentialExpired = credentialExpired;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public Collection<Application> getApplications() {
        return applications;
    }

    public void setApplications(Collection<Application> applications) {
        this.applications = applications;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles.toString() +
                '}';
    }
}

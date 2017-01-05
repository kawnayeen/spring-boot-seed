package com.kawnayeen.logger.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by kawnayeen on 1/5/17.
 */
@Entity
public class Application {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;
    @NotNull
    @Length(max = 32)
    @Column(unique = true)
    private String applicationId;
    @NotNull
    @Length(max = 32)
    @Column(unique = true)
    private String applicationSecret;
    @NotNull
    @Length(max = 32)
    private String displayName;

    @OneToMany(mappedBy = "application")
    private Collection<Log> logs = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private Account account;

    public Application() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplicationSecret() {
        return applicationSecret;
    }

    public void setApplicationSecret(String applicationSecret) {
        this.applicationSecret = applicationSecret;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Collection<Log> getLogs() {
        return logs;
    }

    public void setLogs(Collection<Log> logs) {
        this.logs = logs;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", applicationId='" + applicationId + '\'' +
                ", applicationSecret='" + applicationSecret + '\'' +
                ", displayName='" + displayName + '\'' +
                '}';
    }
}

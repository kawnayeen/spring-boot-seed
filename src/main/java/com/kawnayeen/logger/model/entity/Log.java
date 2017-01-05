package com.kawnayeen.logger.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kawnayeen.logger.model.LogLevel;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by kawnayeen on 1/5/17.
 */
@Entity
public class Log {
    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "APP_ID")
    private Application application;
    @NotNull
    @Length(max = 256)
    private String logger;
    @Enumerated(EnumType.STRING)
    private LogLevel level;
    @NotNull
    @Length(max = 2048)
    private String message;

    public Log() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public LogLevel getLevel() {
        return level;
    }

    public void setLevel(LogLevel level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLogger() {
        return logger;
    }

    public void setLogger(String logger) {
        this.logger = logger;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", logger='" + logger + '\'' +
                ", level=" + level +
                ", message='" + message + '\'' +
                '}';
    }
}

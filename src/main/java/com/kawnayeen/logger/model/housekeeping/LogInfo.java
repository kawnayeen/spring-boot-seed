package com.kawnayeen.logger.model.housekeeping;

import com.kawnayeen.logger.model.LogLevel;

/**
 * Created by kawnayeen on 1/5/17.
 */
public class LogInfo {
    private String applicationId;
    private String logger;
    private LogLevel logLevel;
    private String message;

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getLogger() {
        return logger;
    }

    public void setLogger(String logger) {
        this.logger = logger;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "LogInfo{" +
                "applicationId='" + applicationId + '\'' +
                ", logger='" + logger + '\'' +
                ", logLevel=" + logLevel +
                ", message='" + message + '\'' +
                '}';
    }
}

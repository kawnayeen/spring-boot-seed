package com.kawnayeen.logger.model.housekeeping;

/**
 * Created by kawnayeen on 1/5/17.
 */
public class DisplayName {
    private String displayName;

    public DisplayName(String displayName) {
        this.displayName = displayName;
    }

    public DisplayName(){

    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return "DisplayName{" +
                "displayName='" + displayName + '\'' +
                '}';
    }
}

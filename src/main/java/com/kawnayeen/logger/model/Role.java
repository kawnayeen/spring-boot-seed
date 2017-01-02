package com.kawnayeen.logger.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by kawnayeen on 1/2/17.
 */
@Entity
public class Role {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String code;
    @NotNull
    private String label;

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Role{" +
                "label='" + label + '\'' +
                '}';
    }
}

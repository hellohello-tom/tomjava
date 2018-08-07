package com.tom.model.dto;


import java.io.Serializable;

public class GetUserLoginRoleDto implements Serializable {

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    private String displayName;

    private Integer rid;
}

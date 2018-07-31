package com.tom.model.dto;

import com.tom.model.Role;

import java.util.List;

/**
 *
 * 功能描述: 获取模块与的角色信息
 *
 * @param:
 * @return: 
 * @auther: 
 * @date:  
 */
public class GetModuleRoleDto {

    private int id;

    private String url;

    private String roles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}

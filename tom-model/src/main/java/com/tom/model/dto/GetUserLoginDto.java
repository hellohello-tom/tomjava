package com.tom.model.dto;

import java.util.List;

public class GetUserLoginDto {

    private Integer id;

    private String account;

    private String password;

    private Integer sex;

    private int ticketVersion;

    //角色
    private List<GetUserLoginRoleDto> roles;

    //操作权限
    private List<String> operators;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public int getTicketVersion() {
        return ticketVersion;
    }

    public void setTicketVersion(int ticketVersion) {
        this.ticketVersion = ticketVersion;
    }

    public List<GetUserLoginRoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<GetUserLoginRoleDto> roles) {
        this.roles = roles;
    }

    public List<String> getOperators() {
        return operators;
    }

    public void setOperators(List<String> operators) {
        this.operators = operators;
    }

    public class GetUserLoginRoleDto {
        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public int getRid() {
            return rid;
        }

        public void setRid(int rid) {
            this.rid = rid;
        }

        private String displayName;

        private int rid;
    }
}

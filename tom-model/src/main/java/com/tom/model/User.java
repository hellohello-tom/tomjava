package com.tom.model;

import com.baomidou.mybatisplus.annotations.TableName;
import com.tom.core.model.AbstractModel;

@TableName("user")
public class User extends AbstractModel {

    private String account;

    private String password;

    private Integer sex;

    private int ticketVersion;

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

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getTicketVersion() {
        return ticketVersion;
    }

    public void setTicketVersion(int ticketVersion) {
        this.ticketVersion = ticketVersion;
    }
}

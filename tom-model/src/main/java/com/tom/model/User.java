package com.tom.model;

import com.baomidou.mybatisplus.annotations.TableName;
import com.tom.core.model.AbstractModel;

@TableName("user")
public class User extends AbstractModel {

    private String account;

    private String password;

    private int sex;

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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}

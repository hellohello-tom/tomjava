package com.tom.web.areas.user.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tom.core.model.dtos.IInputDto;

public class UserLoginInputDto implements IInputDto {

    @JsonProperty(value = "username")
    public String userName;

    @JsonProperty(value = "password")
    public String passWord;

    @JsonProperty(value = "vercode")
    public String verCode;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getVerCode() {
        return verCode;
    }

    public void setVerCode(String verCode) {
        this.verCode = verCode;
    }
}

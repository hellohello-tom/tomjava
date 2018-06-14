package com.tom.web.areas.user.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tom.core.model.dtos.IInputDto;
import com.tom.web.core.validators.ValidatorPhone;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserLoginInputDto implements IInputDto {

    @Size(min = 1, max = 20, message = "用户名长度不正确")
    @NotBlank(message = "用户名不能为空")
    @ValidatorPhone
    @JsonProperty(value = "username")
    private String userName;

    @JsonProperty(value = "password")
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码格式不正确")
    private String passWord;

    @JsonProperty(value = "vercode")
    @Size(max = 6, message = "验证码格式不正确")
    private String verCode;

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

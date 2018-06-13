package com.tom.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tom.core.utils.HttpCode;

import java.io.Serializable;
import java.util.Map;

public class AjaxResponse implements Serializable {

    @JsonIgnore
    private transient HttpCode statusCode = HttpCode.OK;// 是否成功

    private boolean success;

    private String message = "操作成功";

    private Map<String, Object> data;// 其他参数


    public Map<String, Object> getdata() {
        return data;
    }

    public void setdata(Map<String, Object> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpCode statusCode) {
        this.statusCode = statusCode;
    }

    public int getSuccess() {
        return statusCode == HttpCode.OK ? 1 : 0;
    }
}

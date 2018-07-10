package com.tom.core.exception;

import java.util.Map;

public class UserFriendlyException extends GlobalException {

    private Map<String,Object> data;

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public UserFriendlyException(String message) {
        super(message);
    }
    
}

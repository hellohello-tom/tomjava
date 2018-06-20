package com.tom.core.utils;

import com.tom.core.model.AjaxResponse;

import java.util.HashMap;
import java.util.Map;

public class AjaxCallBacker {
    /**
     *
     * @param message
     *
     * @param data
     * @return
     */

    public static AjaxResponse Success() {
        return Success("操作成功", new HashMap<String, Object>());
    }

    /**
     *
     * @param message
     * 消息内容
     * @param data 数据
     * @return
     */
    public static AjaxResponse Success(String message,Map<String,Object> data) {
        AjaxResponse ajax = new AjaxResponse();
        ajax.setStatusCode(HttpCode.OK);
        ajax.setMessage(message);
        ajax.setdata(data);
        return ajax;
    }

    /**
     *
     * @param message
     * @param data
     * @return
     */
    public static AjaxResponse Faild(String message,Map<String,Object> data) {
        AjaxResponse ajax = new AjaxResponse();
        ajax.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
        ajax.setMessage(message);
        ajax.setdata(data);
        return ajax;
    }

    /**
     *
     * @param message
     * @param data
     * @return
     */
    public static AjaxResponse Faild(String message,HttpCode httpCode) {
        AjaxResponse ajax = new AjaxResponse();
        ajax.setStatusCode(httpCode);
        ajax.setMessage(message);
        ajax.setdata(new HashMap<>());
        return ajax;
    }

    /**
     *
     * @param message
     * @param data
     * @return
     */
    public static AjaxResponse Faild() {
        AjaxResponse ajax = new AjaxResponse();
        ajax.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
        ajax.setMessage("操作失败");
        ajax.setdata(new HashMap<String, Object>());
        return ajax;
    }
}

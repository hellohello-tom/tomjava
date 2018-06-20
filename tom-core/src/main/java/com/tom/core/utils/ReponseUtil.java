package com.tom.core.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tom.core.model.AjaxResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ReponseUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReponseUtil.class);

    /**
     * 判断是否AJAX请求
     */
    public static boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
    }

    /**
     * REST失败响应
     */
    public static void restFailed(HttpServletResponse response, HttpCode code, String message) {
        respondJson(response, HttpServletResponse.SC_BAD_REQUEST, code, message);
    }

    /**
     * AJAX成功响应
     */
    public static void ajaxSucceed(HttpServletResponse response, HttpCode code, String message) {
        respondJson(response, HttpServletResponse.SC_OK, code, message);
    }

    /**
     * AJAX失败响应
     */
    public static void ajaxFailed(HttpServletResponse response
            , int respondStatus, HttpCode code, String message) {
        respondJson(response, respondStatus, code, message);
    }

    /**
     * JSON响应
     */
    private static void respondJson(HttpServletResponse response
            , int respondStatus, HttpCode code, String message) {
        AjaxResponse ajaxResponse = AjaxCallBacker.Faild(message, code);
        response.setStatus(respondStatus);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            String json = new ObjectMapper().writeValueAsString(ajaxResponse);
            out.write(json);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (out != null)
                out.close();
        }
    }
}

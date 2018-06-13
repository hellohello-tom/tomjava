package com.tom.web.areas;

import com.tom.core.exception.UserFriendlyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ApiControllerBase {

    protected final Logger logger = LoggerFactory.getLogger(getClass());



    @ExceptionHandler(Exception.class)
    public void exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex)
            throws Exception {
        if (ex instanceof UserFriendlyException) {
        } /*
         * else if (ex instanceof IllegalArgumentException) { new
         * IllegalParameterException(ex.getMessage()).handler(modelMap); }
         * else if (ex instanceof UnauthorizedException) {
         * modelMap.put("httpCode", HttpCode.FORBIDDEN.value());
         * modelMap.put("msg", StringUtils.defaultIfBlank(ex.getMessage(),
         * HttpCode.FORBIDDEN.msg())); }
         */else {
            logger.error("SERVER_ERROR", ex);

        }
        String jsonResultStr = "";
        response.setContentType("application/json;charset=UTF-8");
        logger.info(jsonResultStr);
        response.getOutputStream().write(jsonResultStr.getBytes("UTF-8"));
    }
}

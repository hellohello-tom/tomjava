package com.tom.web.areas;

import com.tom.core.exception.UserFriendlyException;
import com.tom.core.redis.RedisCacher;
import com.tom.web.core.controller.TomControllerBase;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static java.util.stream.Collectors.toList;

public abstract class ApiControllerBase extends TomControllerBase {


    @Autowired
    protected RedisCacher redisCacher;

    protected void CheckModelStatus(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            throw new UserFriendlyException(Strings.join(allErrors.stream().map(ObjectError::getDefaultMessage)
                    .collect(toList()), ';'));
        }
    }

//
//    @ExceptionHandler(Exception.class)
//    public void exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex)
//            throws Exception {
//        String message="";
//        if (ex instanceof UserFriendlyException) {
//            message = ex.getMessage();
//        } /*
//         * else if (ex instanceof IllegalArgumentException) { new
//         * IllegalParameterException(ex.getMessage()).handler(modelMap); }
//         * else if (ex instanceof UnauthorizedException) {
//         * modelMap.put("httpCode", HttpCode.FORBIDDEN.value());
//         * modelMap.put("msg", StringUtils.defaultIfBlank(ex.getMessage(),
//         * HttpCode.FORBIDDEN.msg())); }
//         */ else {
//            logger.error("SERVER_ERROR", ex);
//
//        }
//        String jsonResultStr = message;
//        response.setContentType("application/json;charset=UTF-8");
//        logger.info(jsonResultStr);
//        response.getOutputStream().write(jsonResultStr.getBytes("UTF-8"));
//    }
}

package com.tom.web.core.exception;

import com.tom.core.exception.UserFriendlyException;
import com.tom.core.model.AjaxResponse;
import com.tom.core.utils.AjaxCallBacker;
import com.tom.core.utils.HttpCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GobalExceptionHandle {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public AjaxResponse errorHandler(Exception ex) {
        AjaxResponse result = AjaxCallBacker.Faild(ex.getMessage(), HttpCode.INTERNAL_SERVER_ERROR);
        if (ex instanceof UserFriendlyException) {
            UserFriendlyException userFriendlyException = (UserFriendlyException) ex;
            logger.debug(userFriendlyException.getMessage());
            result.setMessage(userFriendlyException.getMessage());
            if (userFriendlyException.getData() != null) {
                result.setdata(userFriendlyException.getData());
            }
        } else if (ex instanceof NoHandlerFoundException) {
            result.setStatusCode(HttpCode.NOT_FOUND);
            result.setMessage("请求的路径不存在");
        } else {
            logger.error("SERVER_ERROR", ex);
        }
        return result;
    }
}

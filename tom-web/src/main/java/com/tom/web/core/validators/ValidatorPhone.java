package com.tom.web.core.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.PARAMETER;

@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {ValidatorPhoneClass.class})
public @interface ValidatorPhone {

    //默认错误消息
    String message() default "电话号码格式不正确";

    //分组
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean canNull() default true;
}
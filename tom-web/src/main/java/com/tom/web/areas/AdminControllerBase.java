package com.tom.web.areas;

import com.tom.web.core.controller.TomControllerBase;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;


@RequestMapping("/admin")
public class AdminControllerBase extends TomControllerBase {
    protected List<String> CheckModelStatus(BindingResult bindingResult) {
        List<String> result = new ArrayList<>();
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            allErrors.stream().map(ObjectError::getDefaultMessage)
                    .collect(toList()).forEach(x->result.add(x));
        }
        return result;
    }
}

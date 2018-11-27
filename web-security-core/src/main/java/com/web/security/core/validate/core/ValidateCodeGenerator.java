package com.web.security.core.validate.core;

import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeGenerator {

    /**
     * 验证码生成方法
     * @param request
     * @return
     */
    ValidateCode generate(ServletWebRequest request);
}

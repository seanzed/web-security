package com.web.security.core;

import com.web.security.core.validate.core.image.ImageCode;
import com.web.security.core.validate.core.ValidateCodeGenerator;
import org.springframework.web.context.request.ServletWebRequest;

//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {

    @Override
    public ImageCode generate(ServletWebRequest request) {
        System.out.println("自定义图形码验证");
        return null;
    }
}

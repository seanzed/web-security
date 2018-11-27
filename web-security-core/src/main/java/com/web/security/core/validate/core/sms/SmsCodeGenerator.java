package com.web.security.core.validate.core.sms;

import com.web.security.core.properties.SecurityProperties;
import com.web.security.core.validate.core.ValidateCode;
import com.web.security.core.validate.core.ValidateCodeGenerator;
import java.util.Random;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

@Data
//@Component("smsCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        int length = ServletRequestUtils
            .getIntParameter(request.getRequest(), "length", securityProperties.getCode().getImage().getLength());

        Random random = new Random();
        String sRand = "";
        for (int i = 0; i < length; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
        }

        return new ValidateCode(sRand, securityProperties.getCode().getImage().getExpireIn());
    }

}

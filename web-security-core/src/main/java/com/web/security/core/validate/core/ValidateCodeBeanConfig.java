package com.web.security.core.validate.core;

import com.web.security.core.properties.SecurityProperties;
import com.web.security.core.validate.core.image.ImageCodeGenerator;
import com.web.security.core.validate.core.sms.DefaultSmsCodeSender;
import com.web.security.core.validate.core.sms.SmsCodeGenerator;
import com.web.security.core.validate.core.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * ConditionalOnMissingBean该注解先查找整个应用有无使用imageGenerateCode名称的其他bean， 如果有使用它; 如果没有，使用此bean
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator() {
        ImageCodeGenerator imageGenerateCode = new ImageCodeGenerator();
        imageGenerateCode.setSecurityProperties(securityProperties);
        return imageGenerateCode;
    }

    /**
     * ConditionalOnMissingBean该注解先查找整个应用有无使用SmsCodeGenerator名称的其他bean， 如果有使用它; 如果没有，使用此bean
     * @return
     */
    @Bean
//    @ConditionalOnMissingBean(SmsCodeGenerator.class)
    @ConditionalOnMissingBean(name = "smsValidateCodeGenerator")
    public ValidateCodeGenerator smsCodeGenerator() {
        SmsCodeGenerator smsGenerateCode = new SmsCodeGenerator();
        smsGenerateCode.setSecurityProperties(securityProperties);
        return smsGenerateCode;
    }

    /**
     * ConditionalOnMissingBean该注解先查找整个应用有无使用SmsCodeSender名称的其他bean， 如果有使用它; 如果没有，使用此bean
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();
    }
}

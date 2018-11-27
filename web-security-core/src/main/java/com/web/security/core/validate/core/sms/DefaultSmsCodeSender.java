package com.web.security.core.validate.core.sms;


public class DefaultSmsCodeSender implements SmsCodeSender {

    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机：" + mobile + "\n 发送验证码： " + code);
    }
}

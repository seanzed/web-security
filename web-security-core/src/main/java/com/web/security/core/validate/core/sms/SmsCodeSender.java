package com.web.security.core.validate.core.sms;

/**
 * 短信发送接口
 */
public interface SmsCodeSender {

    void send(String mobile, String code);
}

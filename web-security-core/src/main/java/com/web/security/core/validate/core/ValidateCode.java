package com.web.security.core.validate.core;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 验证码类
 */
@Data
@AllArgsConstructor
public class ValidateCode {

    private BufferedImage image;

    private String code;

    private LocalDateTime expireTime;

    public ValidateCode(String code,int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    /**
     * 判断是否失效，当前时间是否大于失效时间
     * @return
     */
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}

package com.web.security.core.validate.core.image;

import com.web.security.core.validate.core.ValidateCode;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ImageCode extends ValidateCode {

    private BufferedImage image;

    private String code;

    private LocalDateTime expireTime;

    public ImageCode(BufferedImage image, String code,int expireIn) {
        super(code, expireIn);
        this.image = image;
    }

}

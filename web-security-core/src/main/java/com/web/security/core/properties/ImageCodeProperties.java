package com.web.security.core.properties;

import lombok.Data;

@Data
public class ImageCodeProperties extends SmsCodeProperties {

    private int width = 67;

    private int height = 23;

    public ImageCodeProperties() {
        setLength(4);
    }

}

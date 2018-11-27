package com.web.security.browser.support;

import lombok.Data;

/**
 * @author seanzed@163.com
 */
@Data
public class SimpleResponse {

    private Object content;

    public SimpleResponse(Object content) {
        this.content = content;
    }

}

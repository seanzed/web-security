package com.web.security.service.impl;

import com.web.security.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * 测试Service
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String greeting(String name) {
        return "hello " + name;
    }
}

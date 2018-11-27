package com.web.security.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 过滤器filter可以直接得到
 * ServletRequest对象和ServletResponse对象，但是不可以得到方法名和参数
 */
public class TimeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("time filter init ...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException {
        System.out.println("time filter start ...");
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("执行 time filter 耗时： " + (System.currentTimeMillis() - start));
        System.out.println("time filter finish ...");
    }

    @Override
    public void destroy() {
        System.out.println("time filter destroy ...");
    }
}

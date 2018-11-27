package com.web.security.web.config;

import com.web.security.web.filter.TimeFilter;
import com.web.security.web.interceptor.TimeInterceptor;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * web配置类
 * @Configuration 标记的类必须符合下面的要求：
 *
 * 配置类必须以类的形式提供（不能是工厂方法返回的实例），允许通过生成子类在运行时增强（cglib 动态代理）。
 * 配置类不能是 final 类（没法动态代理）。
 * 配置注解通常为了通过 @Bean 注解生成 Spring 容器管理的类，
 * 配置类必须是非本地的（即不能在方法中声明，不能是 private）。
 * 任何嵌套配置类都必须声明为static。
 * @Bean 方法可能不会反过来创建进一步的配置类（也就是返回的 bean 如果带有 @Configuration，也不会被特殊处理，只会作为普通的 bean）。
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private TimeInterceptor timeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor);
    }

    /**
     * @Bean
     * 用来使用java的配置来管理bean的生命周期。@Bean支持两种属性，即initMethod和destroyMethod, 这些属性可用于定义生命周期属性。
     * 在实例bean和即将销毁对象时， 容器便可调用生命周期方法。 生命周期方法也称为回调方法，因为它将由容器调用。
     * 使用 @Bean 注释注册的 bean 也支持 JSR-250 规定的标准 @PostConstruct 和 @PreDestroy 注释。
     * 如果您正在使用 XML 方法来定义 bean，那么就应该使用 bean 元素来定义生命周期回调方法。
     * 以下代码显示了在 XML 配置中通常使用 bean 元素定义回调的方法。
     * @return
     */
    @Bean
    public FilterRegistrationBean timeFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        TimeFilter timeFilter = new TimeFilter();
        filterRegistrationBean.setFilter(timeFilter);

        List<String> urls = new ArrayList<>();
        urls.add("/*");
        filterRegistrationBean.setUrlPatterns(urls);

        return  filterRegistrationBean;
    }
}

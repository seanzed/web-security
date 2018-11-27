package com.web.security.browser;

import com.web.security.core.properties.SecurityConstants;
import com.web.security.core.properties.SecurityProperties;
import com.web.security.core.validate.core.ValidateCodeSecurityConfig;
import com.web.security.core.validate.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//		tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    /**
     * 创建机密解密bean
     * @return  BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder getBcryptPasswordEncode() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            // 图片验证码配置
            //.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
            // 使用表单认证登陆
            //.formLogin()
            // 使用basic认证登陆
            //http.httpBasic()
            // 配置自定义登录页
            //.loginPage("/authentication/require")
            // 配置自定义登录请求路径
            //.loginProcessingUrl("/authentication/form")
            //.and()
            // 配置登录成功处理器
            //.successHandler(customAuthenticationSuccessHandler)
            // 配置登录失败处理器
            //.failureHandler(customAuthenticationFailureHandler)
            .apply(validateCodeSecurityConfig)
            .and()
            .apply(smsCodeAuthenticationSecurityConfig)
            // 记住密码配置
            .and()
            .rememberMe()
            .tokenRepository(persistentTokenRepository())
            .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
            .userDetailsService(userDetailsService)
            .and()
            .authorizeRequests()
            // 配置无需权限访问的路径。 登录页路径如果不配置这个路径将会显示重定向次数过多的页面
            .antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                securityProperties.getBrowser().getLoginPage(),
                SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*"
                )
            .permitAll()
            .anyRequest()
            .authenticated()
            // 配置跨站伪造为失效， 默认是有效的
            .and()
            .csrf().disable();
    }
}

package com.web.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("输入的用户名：" + username);
        // 根据数据的用户密码进行加密， 然后与数据库密码进行对比
        String password = bCryptPasswordEncoder.encode("123456");
        logger.info("输入的用户密码：" + password);
        // 根据用户名查找用户
        /**
         * 参数说明
         * 1、 用户名
         * 2、 密码
         * 3、 用户是否已被删除， 可以是物理删除和逻辑删除
         * 4、 用户账号是否过期
         * 5、 用户密码是否过期
         * 6、 用户账号是否锁定
         * 7、 用户权限
         */
        return new User(username, password, true,
            true, true, true,
            AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}

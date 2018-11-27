package com.web.security.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.web.security.dto.QueryUserCondition;
import com.web.security.dto.User;
import com.web.security.exception.UserNotExistException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author seanzed@163.com
 * 用户管理Controller层
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户管理", tags = "用户增删改查管理")
public class UserController {

    @GetMapping("/me")
    public Object getCurrent(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }

    @PostMapping
    public User create(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            result.getAllErrors().stream().forEach(errors -> System.out.println(errors.toString()));
        }
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());
        user.setId("1");

        return user;
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable String id) {
        System.out.println(id);
    }

    @PutMapping("/update/{id:\\d+}")
    public User update(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            result.getAllErrors().stream().forEach(errors -> {
                FieldError fieldError = (FieldError) errors;
                /*String message = fieldError.getField() + "." + fieldError.getDefaultMessage();
                System.out.println(message);*/
                System.out.println(errors.getDefaultMessage());
            });
        }
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());
        return user;
    }

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    public List<User> query(QueryUserCondition condition,@PageableDefault(page = 2, size = 10, sort = "username,asc") Pageable pageable) {
        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.DEFAULT_STYLE));

        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());

        return  users;
    }

    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    @ApiOperation(value = "用户个人信息", tags = "测试1")
    public User getInfo(@PathVariable String id) {
//        throw new UserNotExistException(id);
        User user = new User();
        user.setId(id);
        user.setUsername("tom");
        user.setPassword("123456");
        return user;
    }
}

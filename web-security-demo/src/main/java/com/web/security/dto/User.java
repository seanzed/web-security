package com.web.security.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.web.security.validator.MyConstraint;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.validation.constraints.Past;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author seanzed@163.com
 * 用户bean
 */
@ApiModel(description = "用户dto")
public class User {

    public interface UserSimpleView {};
    public interface UserDetailView extends UserSimpleView {};

    private String id;

    @ApiModelProperty("用户名")
    @MyConstraint( message = "这是一个测试注解")
    private String username;

    @ApiModelProperty("用户密码")
    @NotBlank( message = "密码不能为空")
    private String password;

    @ApiModelProperty("用户出生日期")
    @Past( message = "生日必须是过去时间")
    private Date birthday;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonView(UserSimpleView.class)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @JsonView(UserDetailView.class)
    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}

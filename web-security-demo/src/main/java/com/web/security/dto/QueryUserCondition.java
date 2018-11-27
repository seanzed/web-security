package com.web.security.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "用户查询条件")
public class QueryUserCondition {

    @ApiModelProperty("用户Id")
    private String id;

    @ApiModelProperty("用户查询年龄起始值")
    private int age;

    @ApiModelProperty("用户查询年龄结束值")
    private int ageTo;

    private String xxx;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAgeTo() {
        return ageTo;
    }

    public void setAgeTo(int ageTo) {
        this.ageTo = ageTo;
    }

    public String getXxx() {
        return xxx;
    }

    public void setXxx(String xxx) {
        this.xxx = xxx;
    }
}

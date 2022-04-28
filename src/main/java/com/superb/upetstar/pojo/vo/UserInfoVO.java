package com.superb.upetstar.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author hym
 * @description
 */
@Data
@ApiModel("用户信息视图对象")
public class UserInfoVO {
    @ApiModelProperty("昵称")
    private String username;
    @ApiModelProperty("性别")
    private String gender;
    @ApiModelProperty("年龄")
    private Integer age;
    @ApiModelProperty("职业")
    private String career;
    @ApiModelProperty("联系方式")
    private String phone;
    @ApiModelProperty("社区")
    private String communityAddr;
}

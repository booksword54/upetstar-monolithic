package com.superb.upetstar.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author hym
 * @description
 */
@ApiModel("社区管理员登录VO")
@Data
public class CUserLoginVO {
    @ApiModelProperty("管理账号")
    private String cAccount;
    @ApiModelProperty("登陆密码")
    private String cPassword;
}

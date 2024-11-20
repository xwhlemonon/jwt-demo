package com.xwh.user.pojo.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserTokenData implements Serializable {

    @ApiModelProperty("用户名")
    String username;
    @ApiModelProperty("用户设备标识")
    String userAgent;
    @ApiModelProperty("用户IP，暂无")
    String IP;
    @ApiModelProperty("登录索引")
    Integer index;

}

package com.xwh.user.pojo.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserTokenData {

    @ApiModelProperty("用户名")
    String username;
    String userAgent;
    String IP;

}

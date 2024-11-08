package com.xwh.user.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserVO {

    @ApiModelProperty("用户名")
    String username;
    @ApiModelProperty("密码")
    String password;

}

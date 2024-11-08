package com.xwh.user.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserLoginDTO {

    @NotNull
    @ApiModelProperty("用户名")
    String username;
    @NotNull
    @ApiModelProperty("密码")
    String password;

}

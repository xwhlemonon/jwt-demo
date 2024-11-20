package com.xwh.user.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserLoginDTO implements Serializable {

    @NotNull(message = "用户名为空")
    @ApiModelProperty("用户名")
    String username;
    @NotNull(message = "密码为空")
    @ApiModelProperty("密码")
    String password;

}

package com.xwh.user.pojo.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements Serializable {

    @ApiModelProperty("用户名")
    String username;
    @ApiModelProperty("密码")
    String password;

}

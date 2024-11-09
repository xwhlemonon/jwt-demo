package com.xwh.user.base.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum StatusCode {

    SUCCESS(1001, "成功"), //
    FAILURE(2001, "失败");

    Integer code;
    String message;

}

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
public class JsonResult<T> {

    Integer code;
    String message;
    T data;

    public JsonResult(StatusCode statusCode, T data) {
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
        this.data = data;
    }

    public JsonResult(StatusCode statusCode) {
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
    }

    public JsonResult<T> ok(T data) {
        return new JsonResult<>(StatusCode.SUCCESS, data);
    }

    public JsonResult<Void> ok() {
        return new JsonResult<>(StatusCode.SUCCESS);
    }

    public JsonResult<T> no(T data) {
        return new JsonResult<>(StatusCode.FAILURE, data);
    }

    public JsonResult<Void> no() {
        return new JsonResult<>(StatusCode.FAILURE);
    }

}

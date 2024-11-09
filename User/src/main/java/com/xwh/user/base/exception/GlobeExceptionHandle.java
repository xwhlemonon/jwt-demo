package com.xwh.user.base.exception;

import com.xwh.user.base.response.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobeExceptionHandle {

    @ExceptionHandler
    public JsonResult<String> handleThrowable(Throwable e) {
        log.debug("{}", e.getMessage());
        return new JsonResult<String>().no(e.getMessage());
    }

}

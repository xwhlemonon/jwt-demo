package com.xwh.user.controller;

import com.xwh.user.base.response.JsonResult;
import com.xwh.user.base.utils.CookieUtils;
import com.xwh.user.pojo.dto.UserLoginDTO;
import com.xwh.user.pojo.entity.UserTokenData;
import com.xwh.user.pojo.vo.UserLoginVO;
import com.xwh.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Validated
@RestController
@Api(tags = "用户模块")
@RequestMapping("/v1/user/")
public class UserController {

    @Autowired
    private IUserService service;

    @PostMapping("login")
    @ApiOperation("登录接口")
    public JsonResult<UserLoginVO> login(@RequestBody @Validated UserLoginDTO dto, @ApiIgnore HttpServletRequest httpRequest, @ApiIgnore HttpServletResponse httpResponse) {
        log.debug("登录: {}", dto);
        UserLoginVO vo = service.login(dto, httpRequest);
        CookieUtils.reset(httpResponse, vo.getToken());
        return new JsonResult<UserLoginVO>().ok(vo);
    }

    @GetMapping("select")
    @ApiOperation("查询用户接口")
    public JsonResult<UserTokenData> select() {
        UserTokenData vo = service.selectUser();
        return new JsonResult<UserTokenData>().ok(vo);
    }

}


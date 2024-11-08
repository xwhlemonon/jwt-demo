package com.xwh.user.controller;

import com.xwh.user.pojo.dto.UserLoginDTO;
import com.xwh.user.pojo.vo.UserLoginVO;
import com.xwh.user.pojo.vo.UserVO;
import com.xwh.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public String login(@RequestBody @Validated UserLoginDTO dto) {
        log.debug("登录: {}", dto);
        UserLoginVO vo = service.login(dto);
        return vo.toString();
    }

    @GetMapping("select")
    @ApiOperation("查询用户接口")
    public String select(@RequestParam("token") String token) {
        UserVO vo = service.selectUser(token);
        return vo.toString();
    }

}


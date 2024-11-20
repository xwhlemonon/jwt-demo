package com.xwh.user.service.impl;

import com.xwh.user.base.jwt.JwtUtils;
import com.xwh.user.base.utils.CookieUtil;
import com.xwh.user.base.utils.ThreadLocalUtil;
import com.xwh.user.base.utils.RedisUtil;
import com.xwh.user.pojo.dto.UserLoginDTO;
import com.xwh.user.pojo.entity.UserTokenData;
import com.xwh.user.pojo.vo.UserVO;
import com.xwh.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public UserVO login(UserLoginDTO dto, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        Assert.isTrue(dto.getUsername().equals("admin") && dto.getPassword().equals("123"), "用户名或密码错误");
        UserTokenData tokenData = getUserTokenData(dto, httpRequest);
        String token = jwtUtils.sign(tokenData);
        Assert.isTrue(token != null, "数据错误");
        redisUtil.set("user_" + dto.getUsername(), tokenData.getIndex());
        CookieUtil.reset(httpResponse, token);
        UserVO vo = new UserVO();
        BeanUtils.copyProperties(dto, vo);
        return vo;
    }

    private UserTokenData getUserTokenData(UserLoginDTO dto, HttpServletRequest httpRequest) {
        UserTokenData tokenData = new UserTokenData();
        tokenData.setUsername(dto.getUsername());
        tokenData.setUserAgent(httpRequest.getHeader("user-agent"));
        tokenData.setIP("USER-IP");
        Integer index = redisUtil.get("user_" + dto.getUsername(), Integer.class);
        if (index == null) {
            tokenData.setIndex(0);
        } else {
            tokenData.setIndex(index + 1);
        }
        return tokenData;
    }

    @Override
    public UserTokenData selectUser() {
        return ThreadLocalUtil.get(UserTokenData.class);
    }


}

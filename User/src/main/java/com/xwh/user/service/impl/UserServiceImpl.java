package com.xwh.user.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xwh.user.base.jwt.JwtUtils;
import com.xwh.user.pojo.dto.UserLoginDTO;
import com.xwh.user.pojo.entity.UserTokenData;
import com.xwh.user.pojo.vo.UserLoginVO;
import com.xwh.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;

import static com.xwh.user.base.filters.LoginFilters.THREAD_LOCAL;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public UserLoginVO login(UserLoginDTO dto, HttpServletRequest httpRequest) {
        UserTokenData userTokenData = new UserTokenData();
        String header = httpRequest.getHeader("user-agent");
        userTokenData.setUsername(dto.getUsername());
        userTokenData.setUA(header);
        userTokenData.setIP("user-IP");
        String token = jwtUtils.sign(userTokenData);
        Assert.isTrue(token != null, "数据为空");
        UserLoginVO vo = new UserLoginVO();
        vo.setToken(token);
        return vo;
    }

    @Override
    public UserTokenData selectUser() {
        String user = THREAD_LOCAL.get();
        Assert.isTrue(user != null, "未登录");
        ObjectMapper objectMapper = new ObjectMapper();
        UserTokenData vo = null;
        try {
            vo = objectMapper.readValue(user, UserTokenData.class);
        } catch (JsonProcessingException e) {
            log.debug("错误: {}", e.getMessage());
        }
        return vo;
    }

}

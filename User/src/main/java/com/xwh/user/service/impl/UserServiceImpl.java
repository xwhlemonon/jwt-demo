package com.xwh.user.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xwh.user.base.jwt.JwtUtils;
import com.xwh.user.pojo.dto.UserLoginDTO;
import com.xwh.user.pojo.vo.UserLoginVO;
import com.xwh.user.pojo.vo.UserVO;
import com.xwh.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public UserLoginVO login(UserLoginDTO dto) {
        String token = jwtUtils.sign(dto);
        UserLoginVO vo = new UserLoginVO();
        vo.setToken(token);
        return vo;
    }

    @Override
    public UserVO selectUser(String token) {
        String user = jwtUtils.verify(token);
        ObjectMapper objectMapper = new ObjectMapper();
        UserVO vo = null;
        try {
            vo = objectMapper.readValue(user, UserVO.class);
        } catch (JsonProcessingException e) {
            log.debug(e.getMessage());
        }
        return vo;
    }

}

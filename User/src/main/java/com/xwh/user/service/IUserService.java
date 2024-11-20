package com.xwh.user.service;

import com.xwh.user.pojo.dto.UserLoginDTO;
import com.xwh.user.pojo.entity.UserTokenData;
import com.xwh.user.pojo.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IUserService {

    UserVO login(UserLoginDTO dto, HttpServletRequest httpRequest, HttpServletResponse httpResponse);

    UserTokenData selectUser();

}

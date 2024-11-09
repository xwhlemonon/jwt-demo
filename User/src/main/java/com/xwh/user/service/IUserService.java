package com.xwh.user.service;

import com.xwh.user.pojo.dto.UserLoginDTO;
import com.xwh.user.pojo.entity.UserTokenData;
import com.xwh.user.pojo.vo.UserLoginVO;
import com.xwh.user.pojo.vo.UserVO;

import javax.servlet.http.HttpServletRequest;

public interface IUserService {

    UserLoginVO login(UserLoginDTO dto, HttpServletRequest httpRequest);

    UserTokenData selectUser();

}

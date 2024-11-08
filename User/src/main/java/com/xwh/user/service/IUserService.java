package com.xwh.user.service;

import com.xwh.user.pojo.dto.UserLoginDTO;
import com.xwh.user.pojo.vo.UserLoginVO;
import com.xwh.user.pojo.vo.UserVO;

public interface IUserService {

    UserLoginVO login(UserLoginDTO dto);

    UserVO selectUser(String token);

}

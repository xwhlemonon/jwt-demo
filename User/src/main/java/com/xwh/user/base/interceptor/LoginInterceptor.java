package com.xwh.user.base.interceptor;

import com.xwh.user.base.utils.ContextUtil;
import com.xwh.user.base.utils.RedisUtil;
import com.xwh.user.base.utils.ThreadLocalUtil;
import com.xwh.user.pojo.entity.UserTokenData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserTokenData vo = ThreadLocalUtil.get(UserTokenData.class);
        Assert.isTrue(vo != null, "未登录");
        ApplicationContext context = ContextUtil.getApplicationContext();
        RedisUtil redisUtil = (RedisUtil) context.getBean("redisUtil");
        Integer i = redisUtil.get("user_" + vo.getUsername(), Integer.class);
        Assert.isTrue(i.equals(vo.getIndex()), "账号已登出");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }
}

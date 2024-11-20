package com.xwh.user.base.filters;

import com.xwh.user.base.jwt.JwtUtils;
import com.xwh.user.base.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@Component
public class LoginFilters implements Filter {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Cookie[] cookies = httpRequest.getCookies();
        if (cookies != null) {
            String cookieToken = null;
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    cookieToken = cookie.getValue();
                }
            }
            String token = jwtUtils.verify(cookieToken);
            if (token != null) ThreadLocalUtil.set(token);
        }
        chain.doFilter(request, response);
        ThreadLocalUtil.clear();
    }

}

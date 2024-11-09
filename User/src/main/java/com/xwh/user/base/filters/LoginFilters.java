package com.xwh.user.base.filters;

import com.xwh.user.base.jwt.JwtUtils;
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

    public static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Cookie[] cookies = httpRequest.getCookies();
        if (cookies != null) {
            String cookieToken = null;
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("my-token")) {
                    cookieToken = cookie.getValue();
                }
            }
            String headerToken = httpRequest.getHeader("Authorization");
            if (headerToken != null && headerToken.equals(cookieToken)) {
                String token = jwtUtils.verify(headerToken);
                THREAD_LOCAL.set(token);
            }
        }
        chain.doFilter(request, response);
        THREAD_LOCAL.remove();
    }

}

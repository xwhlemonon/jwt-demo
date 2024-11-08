package com.xwh.user.base.filters;

import com.xwh.user.base.jwt.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Slf4j
@Component
public class LoginFilters implements Filter {

    public static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        THREAD_LOCAL.set("OK");
        chain.doFilter(request, response);
        THREAD_LOCAL.remove();
    }

}

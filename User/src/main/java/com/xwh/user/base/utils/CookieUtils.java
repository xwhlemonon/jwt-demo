package com.xwh.user.base.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

    public static void reset(HttpServletResponse httpResponse, String token) {
        Cookie cookie = new Cookie("my-token", token);
        cookie.setMaxAge(1000 * 60 * 60 * 24 * 7);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        httpResponse.addCookie(cookie);
    }

}

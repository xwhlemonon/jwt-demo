package com.xwh.user.base.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    public static void reset(HttpServletResponse httpResponse, String token) {
        Cookie cookie = new Cookie("token", token);
        cookie.setMaxAge(1000 * 60 * 60 * 24 * 7);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        httpResponse.addCookie(cookie);
    }

    public static void remove(HttpServletResponse httpResponse) {
        Cookie cookie = new Cookie("token", "");
        cookie.setMaxAge(0);
        httpResponse.addCookie(cookie);
    }

}

package com.xiao.amovie.utils;

import javax.servlet.http.Cookie;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author xiao
 */
public class CookieUtil {


    public static Cookie findCookie(Cookie[] cookies) {
        if (cookies == null) {
            return null;
        }
        Optional<Cookie> userId = Arrays.stream(cookies).filter(c -> c.getName().equals("userId")).findFirst();

        return userId.orElse(null);


    }
}

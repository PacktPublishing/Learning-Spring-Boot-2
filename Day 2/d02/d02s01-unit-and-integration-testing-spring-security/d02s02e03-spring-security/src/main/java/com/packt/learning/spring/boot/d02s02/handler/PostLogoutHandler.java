package com.packt.learning.spring.boot.d02s02.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostLogoutHandler implements LogoutHandler {

    @Override
    public void logout(final HttpServletRequest httpServletRequest,
                       final HttpServletResponse httpServletResponse,
                       final Authentication authentication) {
        // perform any post-logout operations
    }
}

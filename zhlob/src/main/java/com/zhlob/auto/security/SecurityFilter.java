package com.zhlob.auto.security;

import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SecurityFilter extends DelegatingFilterProxy {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        super.doFilter(servletRequest, response, filterChain);
    }
}

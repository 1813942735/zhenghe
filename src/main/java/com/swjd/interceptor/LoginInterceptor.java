package com.swjd.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception{
    String requstUri=request.getRequestURI();
    if (requstUri.indexOf("login")>=0||requstUri.indexOf("Login")>=0){
        return true;
    }
        HttpSession session=request.getSession();
    if (session.getAttribute("activeName")!=null){
        return true;
    }
    request.getRequestDispatcher("/toLogin").forward(request,response);

        return false;
}
}

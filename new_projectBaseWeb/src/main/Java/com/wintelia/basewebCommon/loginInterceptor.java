package com.wintelia.basewebCommon;

import com.wintelia.baseService.BaseUserService;
import com.wintelia.projectModel.resultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class loginInterceptor implements HandlerInterceptor {

    @Autowired
    private BaseUserService baseUserService;
    @Value("${USER_TOKEN}")
    private String user_token;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requesturl = request.getRequestURI();
        if (requesturl.indexOf("login") > 0 || requesturl.indexOf("code") > 0|| requesturl.indexOf("logout") > 0) {
            return true;
        }
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            response.sendRedirect("/baseweb/login");
            return false;
        }
        String token = "";
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals(user_token)) {
                token = cookies[i].getValue();
                break;
            }
        }
        resultModel resultModel = baseUserService.GetUserModelByToken(token);
        if (resultModel == null) {//没有返回值，异常 重新登录
            return false;
        }
        if (resultModel.getStatus() == 200) {//返回成功   通过
            return true;
        }
        response.sendRedirect("/baseweb/login");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

package com.wintelia.basewebController;

import com.wintelia.baseService.BaseUserService;
import com.wintelia.projectModel.SystemadminModel;
import com.wintelia.projectModel.resultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

    @Value("${USER_TOKEN}")
    private String user_token;

    @Autowired
    private BaseUserService baseUserService;

    @RequestMapping("/")
    public ModelAndView indexpage(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        String token = "";
        for (int i = 0; i < cookies.length; i++) {
            if (user_token.equalsIgnoreCase(cookies[i].getName())) {
                token = cookies[i].getValue();
            }
        }
        SystemadminModel model = null;
        resultModel result = baseUserService.GetUserModelByToken(token);
        if (result.getStatus() == 200)
            model = (SystemadminModel) result.getData();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("usermodel",model);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping("/default")
    public ModelAndView defaultpage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("default");
        return modelAndView;
    }
}

package com.wintelia.basewebController;

import com.wintelia.baseService.BaseUserService;
import com.wintelia.projectModel.SystemadminModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class SystemController {
    @Autowired
    private BaseUserService baseUserService;

    @RequestMapping("/adminuser")
    public ModelAndView SystemAdminUser(HttpServletRequest request, HttpServletResponse response) {
        List<SystemadminModel> userlist = baseUserService.GetAllSystemAdminList();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("systemuser");
        modelAndView.addObject("userlist", userlist);
        return modelAndView;
    }

    @RequestMapping(value = "/userinfo/{userid}", method = RequestMethod.GET)
    public ModelAndView SystemAdminUserInfo(@PathVariable("userid") int userid, HttpServletResponse response, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("usereditor");
        return modelAndView;
    }

}

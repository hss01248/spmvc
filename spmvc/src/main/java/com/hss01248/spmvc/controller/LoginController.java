package com.hss01248.spmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/1/4 0004.
 */

@Controller
public class LoginController {


    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(@RequestParam String  username,@RequestParam String  password,ModelMap modelMap,HttpServletRequest httpServletRequest){



        httpServletRequest.getSession().setAttribute("sessionId",httpServletRequest.getSession().getId());
        String str = "name=---- "+username+"---pw=-------"+password+"---sessionId:"+httpServletRequest.getSession().getId();
        modelMap.addAttribute("info",str);
        return "LoginSuccess";

    }
}

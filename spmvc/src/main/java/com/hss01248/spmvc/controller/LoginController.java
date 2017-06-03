package com.hss01248.spmvc.controller;

import com.hss01248.spmvc.bean.BaseNetBean;
import com.hss01248.spmvc.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/1/4 0004.
 */

@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    UserServiceImpl userService;


    @RequestMapping(value = "login", method = RequestMethod.GET)
    public @ResponseBody BaseNetBean login(@RequestParam String  uname,@RequestParam String  pw,ModelMap modelMap,HttpServletRequest httpServletRequest){



       /* httpServletRequest.getSession().setAttribute("sessionId",httpServletRequest.getSession().getId());
        String str = "name=---- "+uname+"---pw=-------"+pw+"---sessionId:"+httpServletRequest.getSession().getId();
        modelMap.addAttribute("info",str);*/
        return userService.login(uname,pw,httpServletRequest);

    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public @ResponseBody BaseNetBean register(@RequestParam String  phone, @RequestParam String  pw, ModelMap modelMap, HttpServletRequest httpServletRequest){


        return userService.regist(phone,pw,httpServletRequest);

    }


}

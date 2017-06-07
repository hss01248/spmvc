package com.hss01248.spmvc.controller;

import com.hss01248.spmvc.bean.BaseNetBean;
import com.hss01248.spmvc.exception.CustomException;
import com.hss01248.spmvc.service.impl.UserServiceImpl;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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


    /**
     * shiro管理的登录
     * 此方法不处理认证成功,而是会自动跳到上一个页面(可配置)
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/login")
    public String loginsubmit(Model model, HttpServletRequest request)
            throws Exception {

        // shiro在认证过程中出现错误后将异常类路径通过request返回
        String exceptionClassName = (String) request
                .getAttribute("shiroLoginFailure");
        if(exceptionClassName!=null){
            if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
                throw new CustomException("账号不存在");
            } else if (IncorrectCredentialsException.class.getName().equals(
                    exceptionClassName)) {
                throw new CustomException("用户名/密码错误");
            } else if("randomCodeError".equals(exceptionClassName)){
                throw new CustomException("验证码错误");
            } else{
                throw new Exception();//最终在异常处理器生成未知错误
            }
        }
        return "login";

    }


}

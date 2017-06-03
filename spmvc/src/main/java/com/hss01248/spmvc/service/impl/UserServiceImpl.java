package com.hss01248.spmvc.service.impl;

import com.hss01248.spmvc.bean.BaseNetBean;
import com.hss01248.spmvc.mapper.UserMapper2;
import com.hss01248.spmvc.po.User;
import com.hss01248.spmvc.service.UserService;
import com.hss01248.spmvc.util.MyLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/6/3.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper2 userMapper;

    @Override
    public BaseNetBean login(String uname, String pw,HttpServletRequest httpServletRequest) {
        BaseNetBean netBean = new BaseNetBean();
        User user = userMapper.getUser(uname,pw);
        if(user ==null){
            return netBean.setCode(99,"账号或密码错误");
        }
        HttpSession session =  httpServletRequest.getSession(true);//如果当前request没有session,就创建一个
        user.setToken(session.getId());
        session.setAttribute("user",user);
        session.setAttribute("uid",user.getId());
        netBean.setData(user);
        return netBean;
    }

    @Override
    public BaseNetBean regist(String phone, String pw, HttpServletRequest httpServletRequest) {
        int count = userMapper.hasRegister(phone);
        BaseNetBean netBean = new BaseNetBean();
        if(count>0){//已经注册过了
            return netBean.setCode(89,"此号码已被注册");
        }
        User user = new User();
        user.setPhone(phone);
        user.setPw(pw);//todo md5
        int id = userMapper.add(user);
        MyLog.e(user);
        user.setId(id);

        //同时,执行登录操作
       HttpSession session =  httpServletRequest.getSession(true);//如果当前request没有session,就创建一个
        user.setToken(session.getId());
        session.setAttribute("user",user);
        session.setAttribute("uid",id);
        netBean.setData(user);
        return netBean;
    }
}

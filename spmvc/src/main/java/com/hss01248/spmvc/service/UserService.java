package com.hss01248.spmvc.service;

import com.hss01248.spmvc.bean.BaseNetBean;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/6/3.
 */
public interface UserService {

    BaseNetBean login(String  uname, String  pw,HttpServletRequest httpServletRequest);
    BaseNetBean regist(String phone, String pw, HttpServletRequest httpServletRequest);
}

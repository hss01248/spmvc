package com.hss01248.sptest;

import com.hss01248.sptest.base.BaseJunitTest;
import com.hss01248.utils.MyLog;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * Created by Administrator on 2017/6/6.
 */
public class ShiroTest extends BaseJunitTest{

    public static void main(String[] args){
        //testLoginAndOut();
    }



    @Test
    public  void testLoginAndOut(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-first.ini");
        SecurityManager manager = factory.getInstance();
        SecurityUtils.setSecurityManager(manager);//设置到运行环境中

        Subject subject = SecurityUtils.getSubject();//创建一个subject

        AuthenticationToken token = new UsernamePasswordToken("zhangsan","1111111");

        try {
            subject.login(token);
        }catch (AuthenticationException e){
            e.printStackTrace();
        }

       boolean islogin =  subject.isAuthenticated();
        MyLog.d("islogin :"+islogin);

        subject.logout();
        MyLog.e("log out...");

        MyLog.d("islogin :"+subject.isAuthenticated());


    }
}

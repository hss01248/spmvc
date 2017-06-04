package com.hss01248.test;

import com.hss01248.batis.mapper.OrdersMapperCustom;
import com.hss01248.batis.mapper.UserMapper;
import com.hss01248.batis.po.*;
import com.hss01248.utils.MyLog;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/3.
 */
public class FirstBatis {

    SqlSessionFactory factory;
    @Before
    public void init() throws IOException {
        String res = "SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(res);
         factory = new SqlSessionFactoryBuilder().build(inputStream);



    }

    @Test
    public void testFindUserById(){
        SqlSession session =  factory.openSession();
        try {

            User user = new User();

            user.setUsername("王五");
            user.setAddress("北京");
            user.setSex("n");
            user.setBirthday(new Date(2011,5,25));


              int count =  session.insert("com.hss01248.batis.mapper.UserMapper.add",user);
              //user.setId(id);
               session.commit();
            System.out.print(user);
        }catch (Exception e){
            e.printStackTrace();

        }finally {
            session.close();
        }




    }

    @Test
    public void testMapper(){
        SqlSession session =  factory.openSession();
        try {

            User user = new User();

            user.setUsername("王五");
            user.setAddress("北京");
            user.setSex("y");
            user.setBirthday(new Date(2011,5,25));

            UserQueryVo vo = new UserQueryVo();
            UserCustom custom = new UserCustom();
            custom.setUsername("张");
            vo.setUserCustom(custom);


            UserMapper mapper =  session.getMapper(UserMapper.class);
           List<User> users =  mapper.getListByResultMap(vo);
           //int count = mapper.getCount();
            //mapper.del(70);

            //mapper.add(user);


            //user.setId(id);
            //session.commit();
            System.out.print(Arrays.toString(users.toArray()));
            //System.out.print(count);
        }catch (Exception e){
            e.printStackTrace();

        }finally {
            session.close();
        }

    }


    @Test
    public void testOne2One(){
        SqlSession session =  factory.openSession();
        try {

            User user = new User();

            user.setUsername("王五");
            user.setAddress("北京");
            user.setSex("y");
            user.setBirthday(new Date(2011,5,25));

            UserQueryVo vo = new UserQueryVo();
            UserCustom custom = new UserCustom();
            custom.setUsername("张");
            vo.setUserCustom(custom);


            OrdersMapperCustom mapper =  session.getMapper(OrdersMapperCustom.class);
            List<User> customs =  mapper.findUsersAndOrders();
            //int count = mapper.getCount();
            //mapper.del(70);

            //mapper.add(user);


            //user.setId(id);
            //session.commit();
            //System.out.print(Arrays.toString(customs.toArray()));
            MyLog.d(customs);
            //System.out.print(count);
        }catch (Exception e){
            e.printStackTrace();

        }finally {
            session.close();
        }

    }


}

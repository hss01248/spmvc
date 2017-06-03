package com.hss01248.batis.mapper;

import com.hss01248.batis.po.User;
import com.hss01248.batis.po.UserQueryVo;

import java.util.List;

/**
 * Created by Administrator on 2017/6/3.
 */
public interface UserMapper {

    User findById(int id) throws Exception;

    List<User> findByName(String username) throws Exception;

    List<User> findBy(UserQueryVo queryVo) throws Exception;/*包装类型*/

    List<User> getListByResultMap(UserQueryVo queryVo) throws Exception;

    void add(User user) throws Exception;

    void del(int id) throws Exception;

    void update(User user) throws Exception;

    int getCount() throws Exception;

}

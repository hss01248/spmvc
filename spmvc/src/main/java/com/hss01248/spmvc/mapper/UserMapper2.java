package com.hss01248.spmvc.mapper;

import com.hss01248.spmvc.po.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/6/3.
 */
@Repository
public interface UserMapper2 {

    @Insert("insert into user(name, birthday,sex,address,phone,email,avatar,role,location,pw) " +
            "values(#{name}, #{birthday}, #{sex}, #{address}, #{phone}, #{email}, #{avatar}, #{role}, #{location}, #{pw})")
    public int add(User user);

    @Delete("delete from user where id = #{id}")
    public int deleteById(int id);

    @Update("update user set name = #{name}, birthday = #{birthday}, sex = #{sex}, address = #{address}, phone = #{phone}, " +
            "email = #{email}, avatar = #{avatar} , role = #{role} , location = #{location}, pw = #{pw} where id = #{id}" )
    public int update(User user);

    @Select("select * from user where id = #{id}")
    public User getUserById(int id);

    @Select("select * from user")
    public List<User> getAllUsers();

    @Select("select count(*) from user where phone = #{phone}")
    public int hasRegister(String phone);

    @Select("select * from user where phone = #{phone} and pw = #{pw}")
    public User getUser(String phone,String pw);


}

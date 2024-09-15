package com.example.demo.mapper;

import com.example.demo.domain.Admin;
import com.example.demo.domain.Lb;
import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user where username=#{username} and pwd=#{pwd}")
    User userlogin(User user);
    @Select("select * from admin where username=#{username} and pwd=#{pwd}")
    Admin adminlogin(Admin admin);
    @Select("select * from user ")
    List<User> user();

    @Select("SELECT * FROM user WHERE name LIKE concat('%', #{name}, '%')")
    List<User> userbyname(@Param("name") String name);
    @Delete("DELETE FROM `user` WHERE id = #{id}")
    int deleteById(@Param("id") int id);
    @Update("UPDATE `user` SET `status` = #{status} WHERE `id` = #{id}")
    int updateStatusById(@Param("id") Integer id,@Param("status") int status );

    @Insert({
    "INSERT INTO `ebuy`.`user` (`name`, `username`, `pwd`, `phone`, `sex`, `id_number`, `status`, `create_time`, `update_time`, `create_user`, `update_user`)",
    "VALUES (#{name}, #{username}, #{pwd}, #{phone}, #{sex}, #{id_number}, #{status}, #{create_time}, #{update_time}, #{create_user}, #{update_user})"
    })
    int insertUser(User user);
    @Update("UPDATE `user` " +
        "SET `name` = #{name}, `phone` = #{phone}, `sex` = #{sex}, `id_number` = #{id_number} " +
        "WHERE `id` = #{id}")
    int updateUser(User user);


    @Select("SELECT * FROM `user` WHERE id = #{id}")
    User getUserById(int id);

    @Select("SELECT COUNT(*) FROM `user` WHERE `create_time` BETWEEN #{startDate} AND #{endDate}")
    int countUsersDayDate(@Param("startDate") String startDate, @Param("endDate") String endDate);
    @Select("SELECT COUNT(*) FROM `user` WHERE create_time < #{endDate}")
    int countUsersSumDate( @Param("endDate") String endDate);




}

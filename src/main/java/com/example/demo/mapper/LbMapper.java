package com.example.demo.mapper;

import com.example.demo.domain.Lb;
import com.example.demo.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface LbMapper {
    @Select("SELECT * FROM `lb` ")
    List<Lb> getAllLbs();
    @Insert("INSERT INTO `ebuy`.`lb` ( `name`, `status`, `create_time`, `update_time`, `update_user`, `create_user`) " +
            "VALUES (#{name}, #{status}, #{create_time}, #{update_time}, #{update_user}, #{create_user})")
    int insertLb(Lb lb);
    @Delete("DELETE FROM `lb` WHERE id = #{id}")
    int deleteById(@Param("id") int id);
    @Update("UPDATE `lb` SET `status` = #{status} WHERE `id` = #{id}")
    int updateStatusById(@Param("id") Integer id,@Param("status") int status );

}

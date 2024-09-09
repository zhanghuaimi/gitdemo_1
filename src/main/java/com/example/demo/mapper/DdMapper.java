package com.example.demo.mapper;

import com.example.demo.domain.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface DdMapper {
    @Select("SELECT orderitem.*, " +
            "    `order`.status AS `order_status`,\n" +
            "\t\t`order`.ordertime AS `order_ordertime`,\n" +
            "    `user`.phone AS `user_phone`,\n" +
            "    `user`.name AS `user_name`\n" +
            "FROM\n" +
            "    `orderitem`\n" +
            "LEFT JOIN\n" +
            "    `order` ON orderitem.oid = `order`.id\n" +
            "LEFT JOIN\n" +
            "    `user` ON `order`.uid = `user`.id;")



    List<OrderItem> selectOrderItems();
}

package com.example.demo.mapper;

import com.example.demo.pojo.OrderDetailDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface OrderDetailMapper {
    @Select({
            "SELECT",
            "oi.id AS order_item_id,",
            "oi.ddh AS order_item_ddh,",
            "p.name AS product_name,",
            "p.price AS product_price,",
            "o.ordertime AS order_time,",
            "o.status AS order_status,",
            "o.id AS order_id,",
            "u.name AS user_name,",
            "u.phone AS user_phone,",
            "a.address AS address_info",
            "FROM orderitem oi",
            "INNER JOIN `order` o ON oi.oid = o.id",
            "INNER JOIN product p ON oi.pid = p.id",
            "INNER JOIN `user` u ON o.uid = u.id",
            "LEFT JOIN address a ON o.add_id = a.id"
    })
    @Results({
            @Result(property = "orderItemId", column = "order_item_id"),
            @Result(property = "orderItemDdh", column = "order_item_Ddh"),
            @Result(property = "productName", column = "product_name"),
            @Result(property = "productPrice", column = "product_price"),
            @Result(property = "orderTime", column = "order_time"),
            @Result(property = "orderId", column = "order_id"),
            @Result(property = "orderStatus", column = "order_status"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "userPhone", column = "user_phone"),
            @Result(property = "addressInfo", column = "address_info")
    })
    List<OrderDetailDTO> getOrderDetails();
    @Select({
            "SELECT",
            "oi.id AS order_item_id,",
            "oi.ddh AS order_item_ddh,",
            "p.name AS product_name,",
            "p.price AS product_price,",
            "o.ordertime AS order_time,",
            "o.status AS order_status,",
            "u.name AS user_name,",
            "u.phone AS user_phone,",
            "a.address AS address_info",
            "FROM orderitem oi",
            "INNER JOIN `order` o ON oi.oid = o.id",
            "INNER JOIN product p ON oi.pid = p.id",
            "INNER JOIN `user` u ON o.uid = u.id",
            "LEFT JOIN address a ON o.add_id = a.id",
            "WHERE oi.ddh = #{ddh}"
    })
    @Results({
            @Result(property = "orderItemId", column = "order_item_id"),
            @Result(property = "orderItemDdh", column = "order_item_Ddh"),
            @Result(property = "productName", column = "product_name"),
            @Result(property = "productPrice", column = "product_price"),
            @Result(property = "orderTime", column = "order_time"),
            @Result(property = "orderStatus", column = "order_status"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "userPhone", column = "user_phone"),
            @Result(property = "addressInfo", column = "address_info")
    })
    List<OrderDetailDTO> findByDdh(String ddh); // 修改方法名为findByDdh并添加参数ddh
    @Select({
            "SELECT",
            "oi.id AS order_item_id,",
            "oi.ddh AS order_item_ddh,",
            "p.name AS product_name,",
            "p.price AS product_price,",
            "o.ordertime AS order_time,",
            "o.status AS order_status,",
            "u.name AS user_name,",
            "u.phone AS user_phone,",
            "a.address AS address_info",
            "FROM orderitem oi",
            "INNER JOIN `order` o ON oi.oid = o.id",
            "INNER JOIN product p ON oi.pid = p.id",
            "INNER JOIN `user` u ON o.uid = u.id",
            "LEFT JOIN address a ON o.add_id = a.id",
            "WHERE",
            "(#{phone} IS NOT NULL AND u.phone LIKE CONCAT('%', #{phone}, '%'))"

    })
    @Results({
            @Result(property = "orderItemId", column = "order_item_id"),
            @Result(property = "orderItemDdh", column = "order_item_Ddh"),
            @Result(property = "productName", column = "product_name"),
            @Result(property = "productPrice", column = "product_price"),
            @Result(property = "orderTime", column = "order_time"),
            @Result(property = "orderStatus", column = "order_status"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "userPhone", column = "user_phone"),
            @Result(property = "addressInfo", column = "address_info")
    })
    List<OrderDetailDTO> findByPhone(int phone);
    @Select({
            "SELECT",
            "oi.id AS order_item_id,",
            "oi.num AS order_item_num,",
            "oi.ddh AS order_item_ddh,",
            "p.name AS product_name,",
            "p.price AS product_price,",
            "o.ordertime AS order_time,",

            "o.status AS order_status,",
            "u.name AS user_name,",
            "u.phone AS user_phone,",
            "a.address AS address_info",
            "FROM orderitem oi",
            "INNER JOIN `order` o ON oi.oid = o.id",
            "INNER JOIN product p ON oi.pid = p.id",
            "INNER JOIN `user` u ON o.uid = u.id",
            "LEFT JOIN address a ON o.add_id = a.id",
            "WHERE oi.id = #{oi.id}"
    })
    @Results({
            @Result(property = "orderItemNum", column = "order_item_Num"),
            @Result(property = "orderItemId", column = "order_item_id"),
            @Result(property = "orderItemDdh", column = "order_item_Ddh"),
            @Result(property = "productName", column = "product_name"),
            @Result(property = "productPrice", column = "product_price"),
            @Result(property = "orderTime", column = "order_time"),
            @Result(property = "orderStatus", column = "order_status"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "userPhone", column = "user_phone"),
            @Result(property = "addressInfo", column = "address_info")
    })
    List<OrderDetailDTO> findById(int Id); // 修改方法名为findByDdh并添加参数ddh
    @Select({
            "SELECT",
            "oi.id AS order_item_id,",
            "oi.num AS order_item_num,",
            "p.name AS product_name,",
            "p.price AS product_price,",
            "o.ordertime AS order_time,",
            "o.ddh AS ddh,",
            "o.status AS order_status,",
            "u.name AS user_name,",
            "u.phone AS user_phone,",
            "a.address AS address_info",
            "FROM orderitem oi",
            "INNER JOIN `order` o ON oi.oid = o.id",
            "INNER JOIN product p ON oi.pid = p.id",
            "INNER JOIN `user` u ON o.uid = u.id",
            "LEFT JOIN address a ON o.add_id = a.id",
            "WHERE o.id = #{o.id}"
    })
    @Results({
            @Result(property = "orderItemNum", column = "order_item_Num"),
            @Result(property = "orderItemId", column = "order_item_id"),
            @Result(property = "productName", column = "product_name"),
            @Result(property = "productPrice", column = "product_price"),
            @Result(property = "orderTime", column = "order_time"),
            @Result(property = "ddh", column = "ddh"),

            @Result(property = "orderId", column = "order_Id"),
            @Result(property = "orderStatus", column = "order_status"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "userPhone", column = "user_phone"),
            @Result(property = "addressInfo", column = "address_info")
    })
    List<OrderDetailDTO> findById1(int Id);
}

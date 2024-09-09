package com.example.demo.mapper;

import com.example.demo.domain.DailySummary;
import com.example.demo.domain.Order;
import com.example.demo.pojo.OrderDetailDTO;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface OrderMapper {
    @Select({
            "SELECT `order`.id AS orderId,",
            "       `order`.ddh AS orderDdh,",
            "       `order`.ordertime AS orderTime,",
            "       `order`.status AS orderStatus,",
            "       `order`.zj AS zj,",
            "       address.address AS address,",
            "       user.phone AS phone,",
            "       user.name AS name",
            "FROM `order`",
            "INNER JOIN user ON `order`.uid = user.id",
            "INNER JOIN address ON `order`.add_id = address.id"
    })
    @Results({
            @Result(property = "id", column = "orderId"),
            @Result(property = "orderddh", column = "orderDdh"),
            @Result(property = "ordertime", column = "orderTime"),
            @Result(property = "status", column = "orderStatus"),
            @Result(property = "zj", column = "zj"),
            @Result(property = "address", column = "address"), // 假设在你的实体中有'address'属性
            @Result(property = "phone", column = "phone"),
            @Result(property = "name", column = "name")
    })

    List<Order> getOrdersWithDetails();
    @Update("UPDATE `ebuy`.`order` SET `status` = #{status} WHERE `id` = #{id}")
    int updateStatus(@Param("id") int id, @Param("status") int status);

    @Select({
            "SELECT `order`.id AS orderId,",
            "       `order`.ddh AS orderDdh,",
            "       `order`.ordertime AS orderTime,",
            "       `order`.status AS orderStatus,",
            "       `order`.zj AS zj,",
            "       address.address AS address,",
            "       user.phone AS phone,",
            "       user.name AS name",
            "FROM `order`",
            "INNER JOIN user ON `order`.uid = user.id",
            "INNER JOIN address ON `order`.add_id = address.id",
            "WHERE `order`.ddh = #{orderddh}"
    })
    @Results({
            @Result(property = "id", column = "orderId"),
            @Result(property = "orderddh", column = "orderDdh"),
            @Result(property = "ordertime", column = "orderTime"),
            @Result(property = "status", column = "orderStatus"),
            @Result(property = "zj", column = "zj"),
            @Result(property = "address", column = "address"), // 假设在你的实体中有'address'属性
            @Result(property = "phone", column = "phone"),
            @Result(property = "name", column = "name")
    })
    List<Order> searchddh(int ddh);
    @Select({
            "SELECT `order`.id AS orderId,",
            "       `order`.ddh AS orderDdh,",
            "       `order`.ordertime AS orderTime,",
            "       `order`.status AS orderStatus,",
            "       `order`.zj AS zj,",
            "       address.address AS address,",
            "       user.phone AS phone,",
            "       user.name AS name",
            "FROM `order`",
            "INNER JOIN user ON `order`.uid = user.id",
            "INNER JOIN address ON `order`.add_id = address.id",
            "WHERE `user`.phone = #{phone}"
    })
    @Results({
            @Result(property = "id", column = "orderId"),
            @Result(property = "orderddh", column = "orderDdh"),
            @Result(property = "ordertime", column = "orderTime"),
            @Result(property = "status", column = "orderStatus"),
            @Result(property = "zj", column = "zj"),
            @Result(property = "address", column = "address"), // 假设在你的实体中有'address'属性
            @Result(property = "phone", column = "phone"),
            @Result(property = "name", column = "name")
    })
    List<Order> searchphone(String phone);
    @Select("SELECT DATE(`ordertime`) AS date, COALESCE(SUM(zj), 0) AS daily_total FROM `order`\n" +
            "WHERE `ordertime` BETWEEN #{startDate} AND #{endDate}\n" +
            "GROUP BY DATE(`ordertime`)\n" +
            "UNION ALL\n" +
            "SELECT NULL AS date, 0 AS daily_total\n" +
            "FROM DUAL\n" +
            "WHERE NOT EXISTS (\n" +
            "    SELECT 1 FROM `order`\n" +
            "    WHERE `ordertime` BETWEEN #{startDate} AND #{endDate}\n" +
            ");")

    List<DailySummary> getDailyTotalsByDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate);

}







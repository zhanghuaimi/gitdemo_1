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
    @Select("WITH RECURSIVE date_range AS (  \n" +
            "  SELECT #{startDate} AS date  \n" +
            "  UNION ALL  \n" +
            "  SELECT DATE_ADD(date, INTERVAL 1 DAY)  \n" +
            "  FROM date_range  \n" +
            "  WHERE date < #{endDate}  \n" +
            ")  \n" +
            "SELECT   \n" +
            "    dr.date,  \n" +
            "    COALESCE(SUM(o.zj), 0) AS daily_total  \n" +
            "FROM   \n" +
            "    date_range dr  \n" +
            "LEFT JOIN   \n" +
            "    `order` o ON DATE(o.ordertime) = dr.date  \n" +
            "GROUP BY   \n" +
            "    dr.date  \n" +
            "ORDER BY   \n" +
            "    dr.date;")

    List<DailySummary> getDailyTotalsByDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate);
    @Select("SELECT COUNT(id) FROM `order` WHERE ordertime < #{endDate} and `status` = 5")
    int countOrdercuss( @Param("endDate") String endDate);
    @Select("SELECT COUNT(id) FROM `order` WHERE ordertime < #{endDate}")
    int countOrderall( @Param("endDate") String endDate);
}







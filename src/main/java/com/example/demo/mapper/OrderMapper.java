package com.example.demo.mapper;

import com.example.demo.domain.DailySummary;
import com.example.demo.domain.Order;
import com.example.demo.pojo.OrderDetailDTO;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
    @Select("SELECT COUNT(id) FROM `order` WHERE ordertime >= #{startDate} AND ordertime < #{endDate} and `status` = 5;")
    int countOrdercuss(@Param("startDate") String startDate, @Param("endDate") String endDate);
    @Select("SELECT COUNT(id) FROM `order` WHERE ordertime >= #{startDate} AND ordertime < #{endDate}")
    int countOrderall( @Param("startDate") String startDate,@Param("endDate") String endDate);
    @Select("SELECT status, COUNT(*) AS total_orders\n" +
            "FROM `order`\n" +
            "WHERE DATE(ordertime) = #{date}\n" +
            "AND status IN (2, 3, 5, 6)\n" +
            "GROUP BY status")
    List<Map<String, Object>> getOrderStatusCountsByDate(String date);
    @Select("SELECT COUNT(*) FROM `order` where  status = 2")
    int all1();
    @Select({
            "SELECT  ",
            "    o.id,",
            "    o.ddh,",
            "    o.add_id,",
            "    o.ordertime,",
            "    o.zj,",
            "    o.`status`,",
            "    a.address",
            "FROM",
            "    `order` o",
            "JOIN",
            "    address a ON o.add_id = a.id",
            "WHERE `status` = 2 and ordertime >= #{startDate} AND ordertime < #{endDate}",
            "LIMIT",
            "    #{pageSize} OFFSET #{page};"
    })
    List<Map<String, Object>> all2(@Param("page") int page, @Param("pageSize") int pageSize, @Param("startDate") String startDate,@Param("endDate") String endDate);

}







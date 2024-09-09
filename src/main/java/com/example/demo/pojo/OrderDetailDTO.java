package com.example.demo.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class OrderDetailDTO {
    private Long orderItemId;
    private Long orderItemDdh;
    int ddh;
    private String productName;
    private BigDecimal productPrice;
    private LocalDateTime orderTime;
    private int orderId;
    private Integer orderStatus;
    private String userName;
    private String userPhone;
    private String addressInfo;
    private int orderItemNum;


    // 构造函数、Getter和Setter省略
}

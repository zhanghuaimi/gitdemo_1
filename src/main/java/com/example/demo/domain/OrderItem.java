package com.example.demo.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderItem {
    int id;
    int oid;
    int pid;
    int num;
    float sum;
    int ddh;
   int order_status;
    LocalDateTime order_ordertime;
    String user_phone;
    String user_name;
}

package com.example.demo.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Order {
    int id;
    int  productnum;
    int status;
    String  recievename;
    int address_id;
    int zj;
    String address;
    String phone;
    String name;
    int orderddh;
    LocalDateTime ordertime;

    LocalDateTime createtime;
}

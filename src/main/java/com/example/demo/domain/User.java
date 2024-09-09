package com.example.demo.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class User {
    int id;
    String code;
    String username;
    String name;
    String pwd;
    String phone;
    String sex;
    String id_number;
    int status;
    LocalDateTime create_time;
    LocalDateTime update_time;
    Long create_user;
    Long update_user;


}

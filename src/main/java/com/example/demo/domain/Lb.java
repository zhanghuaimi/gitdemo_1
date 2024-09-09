package com.example.demo.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Lb {
    int id;
    String name;
    int status;
    LocalDateTime create_time;
    LocalDateTime update_time;
    Long create_user;
    Long update_user;}


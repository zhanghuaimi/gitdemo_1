package com.example.demo.domain;

import lombok.Data;

@Data
public class Product {
    int id;
    String name;
    float price;
    String img;
    String content;
    int lbid;

    String lbmc;
}

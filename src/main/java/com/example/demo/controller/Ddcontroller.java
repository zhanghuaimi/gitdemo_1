package com.example.demo.controller;

import com.example.demo.mapper.DdMapper;
import com.example.demo.pojo.ResponseResult;
import com.example.demo.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Ddcontroller {
    @Autowired
    DdMapper ddMapper;
    @GetMapping("getOrderDetails")
    public Result getOrderDetails(){
        return ResponseResult.succ("查询成功",ddMapper.selectOrderItems());
    }


}

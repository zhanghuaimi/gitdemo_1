package com.example.demo.controller;

import com.example.demo.mapper.OrderDetailMapper;
import com.example.demo.pojo.OrderDetailDTO;
import com.example.demo.pojo.ResponseResult;
import com.example.demo.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderDetailService {

    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @GetMapping("/all")
    public Result all(){
        return ResponseResult.succ("查询成功",orderDetailMapper.getOrderDetails());
    }
    @GetMapping("/findByDdh")
    public Result findByDdh(@RequestParam String ddh) {
        // 使用@RequestParam注解明确指定请求参数名，尽管默认情况下参数名与方法参数名相同
        List<OrderDetailDTO> details = orderDetailMapper.findByDdh(ddh);
        // 正确地传递ddh参数到findByDdh方法
        return ResponseResult.succ("查询成功", details);
    }
    @GetMapping("/findByPhone")
    public Result findByPhone(@RequestParam int phone) {
        // 使用@RequestParam注解明确指定请求参数名，尽管默认情况下参数名与方法参数名相同
        List<OrderDetailDTO> details = orderDetailMapper.findByPhone(phone);
        // 正确地传递ddh参数到findByDdh方法
        return ResponseResult.succ("查询成功", details);
    }
    @GetMapping("/findById")
    public Result findById(@RequestParam int id) {
        // 使用@RequestParam注解明确指定请求参数名，尽管默认情况下参数名与方法参数名相同
        List<OrderDetailDTO> details = orderDetailMapper.findById(id);
        // 正确地传递ddh参数到findByDdh方法
        return ResponseResult.succ("查询成功", details);
    }
    @GetMapping("/findById1")
    public Result findById1(@RequestParam int id) {
        // 使用@RequestParam注解明确指定请求参数名，尽管默认情况下参数名与方法参数名相同
        List<OrderDetailDTO> details = orderDetailMapper.findById1(id);
        // 正确地传递ddh参数到findByDdh方法
        return ResponseResult.succ("查询成功", details);
    }

}
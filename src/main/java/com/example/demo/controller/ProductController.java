package com.example.demo.controller;

import com.example.demo.aop.LogCheck;
import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.pojo.ResponseResult;
import com.example.demo.pojo.Result;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductMapper productMapper;

    @GetMapping("/all")
    public Result all(){
        return ResponseResult.succ("查询成功",productMapper.selectAll());
    }

    @PostMapping("/product/xg")
    public Result xg(@RequestBody Product product){
        return ResponseResult.succ("修改成功",productMapper.update(product));
    }
    @PostMapping("/product/add")
    public Result add(@RequestBody Product product){
        return ResponseResult.succ("增加成功",productMapper.insert(product));
    }
    @GetMapping("/product/del")
    public Result del(int id){
        return ResponseResult.succ("删除成功",productMapper.delete(id));
    }
    @GetMapping("/lb")
    public Result lb(){
        return ResponseResult.succ("查询成功",productMapper.selectLb());
    }
    @GetMapping("/getone")
    public Result one(Integer id){
        return ResponseResult.succ("查询成功",productMapper.selectOne(id));
    }
    @GetMapping("/selectProductsByName")
    public Result selectProductsByName(String name){
        List<Product> products = productMapper.selectProductsByName(name);
        if (products.isEmpty()) {
            // 如果列表为空，返回一个表示没有找到用户的响应
            return ResponseResult.fail("未找到匹配的用户");
        } else {
            // 如果列表不为空，返回成功的响应
            return ResponseResult.succ("查询成功", products);
        }
    }
    @GetMapping("/countProducts")
    public Result countProducts(){
        return ResponseResult.succ("查询成功",productMapper.countProducts());
    }



}

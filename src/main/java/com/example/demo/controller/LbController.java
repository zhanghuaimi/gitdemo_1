package com.example.demo.controller;

import com.example.demo.domain.Lb;
import com.example.demo.mapper.LbMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.ResponseResult;
import com.example.demo.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@RestController
public class LbController {
    @Autowired
    LbMapper lbMapper;
    @Autowired
    HttpSession session;
    @GetMapping("/getAllLbs")
    public Result getAllLbs() {
        return ResponseResult.succ("查询成功",lbMapper.getAllLbs());
    }
    @PostMapping("/insertLb")
    public Result insertLb(@RequestBody Lb lb) {
        lb.setCreate_time(LocalDateTime.now()); // 设置当前时间为create_time的值
        lb.setUpdate_user(1l);
        lb.setCreate_user(1l);
        lb.setUpdate_time(LocalDateTime.now()); // 为update_time属性赋值
        return ResponseResult.succ("添加成功",lbMapper.insertLb(lb));
    }
    @GetMapping("/deleteById")
    public Result deleteById(int id) {
        return ResponseResult.succ("删除成功",lbMapper.deleteById(id));
    }
    @GetMapping("/updateStatusById1")
    public Result updateStatusById( Integer id,int status){
        return ResponseResult.succ("修改成功",lbMapper.updateStatusById(id,status));

    }
}

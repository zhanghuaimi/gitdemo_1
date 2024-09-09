package com.example.demo.controller;

import com.example.demo.domain.Admin;
import com.example.demo.domain.Product;
import com.example.demo.domain.User;
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
import java.util.Date;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    HttpSession session;
    @GetMapping("/userlogin")
    public Result userlogin(User user){
        User user1 = userMapper.userlogin(user);
        if(user1==null){
            return ResponseResult.fail("用户名或密码错误！");
        }else {
            session.setAttribute("user",user1);
            return ResponseResult.succ("普通用户登录成功",null);
        }
    }
    @GetMapping("/adminlogin")
    public Result adminlogin(Admin user){
        Admin user1 = userMapper.adminlogin(user);
        if(user1==null){
            return ResponseResult.fail("用户名或密码错误！");
        }else {
            session.setAttribute("admin",user1);
            return ResponseResult.succ("管理用户登录成功",null);
        }
    }
    @GetMapping("/admin")
    public Result admin() {
            return ResponseResult.succ("查询成功",userMapper.user());
    }
    @GetMapping("/userbyname")
    public Result userbyname(String name) {
        List<User> users = userMapper.userbyname(name);
        if (users.isEmpty()) {
            // 如果列表为空，返回一个表示没有找到用户的响应
            return ResponseResult.fail("未找到匹配的用户");
        } else {
            // 如果列表不为空，返回成功的响应
            return ResponseResult.succ("查询成功", users);
        }
    }
    @GetMapping("/userdeleteById")
    public Result deleteById(int id){
        return ResponseResult.succ("删除成功",userMapper.deleteById(id));
    }
    @GetMapping("/updateStatusById")
    public Result updateStatusById( Integer id,int status){
        return ResponseResult.succ("修改成功",userMapper.updateStatusById(id,status));

    }

    @PostMapping("/insertUser") // 改为POST请求
    public Result insertUser(@RequestBody User user) {
        user.setCreate_time(LocalDateTime.now()); // 设置当前时间为create_time的值
        user.setCreate_user(1l);
        user.setUpdate_user(1l);
        user.setPwd("123456"); // 设置密码为123456
        user.setUpdate_time(LocalDateTime.now()); // 为update_time属性赋值
        return ResponseResult.succ("插入成功", userMapper.insertUser(user));
    }
    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody User user) {
        user.setUpdate_time(LocalDateTime.now());
        return ResponseResult.succ("修改成功",userMapper.updateUser(user));

    }
    @GetMapping("/getUserById")
    public Result getUserById(int id) {
        return ResponseResult.succ("查询成功",userMapper.getUserById(id));
    }








}

package com.example.demo.controller;

import com.example.demo.domain.DailySummary;
import com.example.demo.domain.Order;
import com.example.demo.mapper.OrderDetailMapper;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.pojo.OrderDetailDTO;
import com.example.demo.pojo.ResponseResult;
import com.example.demo.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders1")
public class OrderController {
    @Autowired
    OrderMapper OrderMapper;

    @GetMapping("/all")
    public Result all(){
        return ResponseResult.succ("查询成功", OrderMapper.getOrdersWithDetails());
    }
    @PostMapping("/updateStatus")
    public Result updateStatus(@RequestBody Order order ){
    int id = order.getId();
    int status = order.getStatus();
        return ResponseResult.succ("修改成功" ,OrderMapper.updateStatus(id, status));
    }
    @GetMapping("/searchddh")
    public Result searchddh( int ddh){
        return ResponseResult.succ("查询成功" ,OrderMapper.searchddh(ddh));
    }
    @GetMapping("/searchphone")
    public Result searchphone( String phone){
        return ResponseResult.succ("查询成功" ,OrderMapper.searchphone(phone));
    }

    @GetMapping("/getOrdersByDateRange")
    public Result getOrdersByDateRange(@RequestParam("startDate") String startDateStr,
                                       @RequestParam("endDate") String endDateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate startDate = LocalDate.parse(startDateStr, formatter);
        LocalDate endDate = LocalDate.parse(endDateStr, formatter);

        List<DailySummary> dailyTotals = OrderMapper.getDailyTotalsByDateRange(startDateStr, endDateStr);

        Result result = new Result();
        result.setCode(200); // 直接设置为 200
        result.setMsg("Success"); // 直接设置为 Success
        result.setData(dailyTotals); // 返回查询结果

        return result;
    }

    @GetMapping("/countOrdercuss")
    public Result countOrdercuss(@RequestParam String startDate, @RequestParam String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 解析开始和结束日期
        LocalDate startLocalDate = LocalDate.parse(startDate, formatter);
        LocalDate endLocalDate = LocalDate.parse(endDate, formatter);

        // 拼接完整的日期时间字符串
        String currentStartDate = startLocalDate.atStartOfDay().format(formatter) + " 00:00:00";
        String currentEndDate = endLocalDate.atTime(23, 59, 59).format(formatter) + " 23:59:59";

        // 查询订单数量
        int count = OrderMapper.countOrdercuss(currentStartDate, currentEndDate);

        // 返回结果
        return ResponseResult.succ("查询成功", count);
    }
    @GetMapping("/countOrderall")
    public Result countOrderall(@RequestParam String startDate, @RequestParam String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 解析开始和结束日期
        LocalDate startLocalDate = LocalDate.parse(startDate, formatter);
        LocalDate endLocalDate = LocalDate.parse(endDate, formatter);

        // 拼接完整的日期时间字符串
        String currentStartDate = startLocalDate.atStartOfDay().format(formatter) + " 00:00:00";
        String currentEndDate = endLocalDate.atTime(23, 59, 59).format(formatter) + " 23:59:59";

        // 查询订单数量
        int count = OrderMapper.countOrderall(currentStartDate, currentEndDate);

        // 返回结果
        return ResponseResult.succ("查询成功", count);
    }
    @GetMapping("/getOrderStatusCountsByDate")
    public List<Map<String, Object>> getOrderStatusCountsByDate(@RequestParam String date) {
        return OrderMapper.getOrderStatusCountsByDate(date);
    }
    @GetMapping("all1")
    public Result all1() {
        return ResponseResult.succ("查询成功",OrderMapper.all1());
    }
    @GetMapping("all2")
    public Result all2(int page , int pageSize,@RequestParam String startDate, @RequestParam String endDate) {
        int start = (page - 1) * pageSize;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 解析开始和结束日期
        LocalDate startLocalDate = LocalDate.parse(startDate, formatter);
        LocalDate endLocalDate = LocalDate.parse(endDate, formatter);

        // 拼接完整的日期时间字符串
        String currentStartDate = startLocalDate.atStartOfDay().format(formatter) + " 00:00:00";
        String currentEndDate = endLocalDate.atTime(23, 59, 59).format(formatter) + " 23:59:59";

        List<Map<String, Object>> data = OrderMapper.all2(start, pageSize,currentStartDate,currentEndDate);
        return ResponseResult.succ("查询成功",data );
    }


}





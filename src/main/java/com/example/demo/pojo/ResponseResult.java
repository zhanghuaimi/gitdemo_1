package com.example.demo.pojo;

import org.springframework.http.HttpStatus;

public class ResponseResult {
    public static Result succ(String msg,Object data){
        Result result =new Result();
        result.setCode(200);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static Result fail(String msg){
        Result result =new Result();
        result.setCode(0);
        result.setMsg(msg);
        return result;
    }
public static Result conflict(String msg) {
    Result result = new Result();
    result.setCode(HttpStatus.CONFLICT.value());
    result.setMsg(msg);
    return result;
}




}

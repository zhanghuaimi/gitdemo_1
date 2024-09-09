package com.example.demo.yc;

import com.example.demo.pojo.ResponseResult;
import com.example.demo.pojo.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Result handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
        String message = "资源冲突，可能是由于违反了唯一性约束。";
        // 解析具体的错误信息，例如从ex.getMessage()中提取
        if (ex.getMessage().contains("Duplicate entry")) {
            String[] parts = ex.getMessage().split("'");
            if (parts.length >= 3) {
                String duplicateValue = parts[1];
                String constraintName = parts[3];
                if ("user.idx_unique_username".equals(constraintName)) {
                    message = "账号 '" + duplicateValue + "' 已经存在，请选择一个不同的账号。";
                }
            }
        }
        return ResponseResult.conflict(message);
    }
}

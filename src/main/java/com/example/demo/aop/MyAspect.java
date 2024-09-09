package com.example.demo.aop;

import com.example.demo.pojo.ResponseResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
@Aspect
public class MyAspect {
    @Autowired
    HttpSession session;
    @Around("@annotation(logCheck)")
    public Object around(ProceedingJoinPoint joinPoint,LogCheck logCheck) throws Throwable {
        String value = logCheck.value();
        Object attribute = session.getAttribute(value);
        if(attribute==null){
            return ResponseResult.fail("你还没有登录");
        }else {
            return joinPoint.proceed();
        }
    }
}

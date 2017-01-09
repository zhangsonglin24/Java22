package com.kaishengit.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AopAspect {
    @Pointcut("execution(* com.kaishengit.service..*.*(..))")
    public void pointcut(){}

    @Before("pointcut()")
    public void beforeAdvice(){
        System.out.println("前置通知");
    }

    @AfterReturning("pointcut()")
    public void afterAdvice(){
        System.out.println("后置通知");
    }

    @AfterThrowing("pointcut()")
    public void exceptionAdvice(){
        System.out.println("异常通知");
    }

    @After("pointcut()")
    public void finallyAdvice(){
        System.out.println("最终通知");
    }


    public void aroundAdvice(ProceedingJoinPoint joinPoint){
        try{
            System.out.println("!!!!!!!!!前置通知!!!!!!!!!!!!!!");
            Object result = joinPoint.proceed();//目标对象的方法执行
            System.out.println("!!!!!!!!!!!!!!后置通知!!!!!!!!");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("!!!!!!!!异常通知!!!!!!!!!!!");
        }finally {
            System.out.println("!!!!!!!!最终通知!!!!!!!!!");
        }
    }

}

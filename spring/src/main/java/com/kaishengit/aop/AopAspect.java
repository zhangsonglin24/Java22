package com.kaishengit.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class AopAspect {

    public void beforeAdvice(){
        System.out.println("前置通知");
    }

    public void afterAdvice(){
        System.out.println("后置通知");
    }

    public void exceptionAdvice(){
        System.out.println("异常通知");
    }

    public void finallyAdvice(){
        System.out.println("最终通知");
    }

    public void aroundAdvice(ProceedingJoinPoint joinPoint){
        try{
            System.out.println("!!!!!!!!!前置通知!!!!!!!!!!!!!!");
            Object result = joinPoint.proceed();
            System.out.println("!!!!!!!!!!!!!!后置通知!!!!!!!!");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("!!!!!!!!异常通知!!!!!!!!!!!");
        }finally {
            System.out.println("!!!!!!!!最终通知!!!!!!!!!");
        }
    }

}

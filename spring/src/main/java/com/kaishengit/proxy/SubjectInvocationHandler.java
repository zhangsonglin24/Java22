package com.kaishengit.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SubjectInvocationHandler implements InvocationHandler {

    private Object target;

    public SubjectInvocationHandler(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("before,,,,,,,,,");
        //目标对象方法的执行
        Object result = method.invoke(target,args);
        System.out.println("after,,,,,,,,,,,,");
        return result;
    }
}

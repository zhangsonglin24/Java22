package com.kaishengit.proxy;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SubjectInvocationHandler implements InvocationHandler {

    private Object target;

    public SubjectInvocationHandler(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        try {
            System.out.println("before,,,,,,,,,");
            //目标对象方法的执行
             result = method.invoke(target, args);
            System.out.println("after,,,,,,,,,,,,");
        }catch (Exception ex){
            System.out.println("exception...............");
        }finally {
            System.out.println("finally.,,,,,,,,,,,");
        }
            return result;
    }
}

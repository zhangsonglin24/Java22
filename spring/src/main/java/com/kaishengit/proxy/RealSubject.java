package com.kaishengit.proxy;

public class RealSubject implements Subject {
    @Override
    public void sayHello() {
        System.out.println("RealSubject,hello!!!!!!!!!!");
    }
}

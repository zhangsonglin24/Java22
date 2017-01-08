package com.kaishengit.proxy;

public class ProxySubject implements Subject {

    private Subject subject = new RealSubject();

    @Override
    public void sayHello() {
        subject.sayHello();
    }
}

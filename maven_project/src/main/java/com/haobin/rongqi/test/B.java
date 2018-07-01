package com.haobin.rongqi.test;

import com.haobin.others.A;

public class B extends A {
    public void k() {
        super.f();   //子类B和父类A不在同一个包里面，但是在子类B中是可以访问到A的protected方法的
    }
}

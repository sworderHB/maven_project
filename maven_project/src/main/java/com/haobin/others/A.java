package com.haobin.others;

import java.lang.ref.WeakReference;

import org.junit.Test;

public class A {
    public String name = "aaa";
    private int age;
    private String sex;
    private float score;
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + age;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        A other = (A) obj;
        if (age != other.age)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    protected void f() {}
    
    public void g() {}
    
    @Test
    public void test1() {
    	A productA = new A();
    	WeakReference<A> weakProductA = new WeakReference<A>(productA);

    }
}

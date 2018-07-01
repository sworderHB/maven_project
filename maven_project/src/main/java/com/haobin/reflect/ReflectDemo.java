package com.haobin.reflect;

class Student {
    public Student() {
        System.out.println("Student类的构造方法");
    }

    @Override
    public String toString() {
        return "Student类的toString方法";
    }
}

public class ReflectDemo {
    public static void main(String[] args) throws Exception {
        Class<?> cls = Class.forName("com.haobin.reflect.Student");
        // 相当于关键字实例化对象,Object obj = new Student();
        Object obj = cls.newInstance();   // 输出 Student类的构造方法
        System.out.println(obj);    // 输出 Student类的toString方法
    }
}

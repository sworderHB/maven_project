package com.haobin.rongqi.test;

import java.util.Set;
import java.util.TreeSet;

public class Test_001 {
    public static void main(String[] args) {
        Set<Person> ts = new TreeSet<Person>();

        Person p1 = new Person(10, "aa");
        Person p2 = new Person(10, "bb");
        Person p3 = new Person(10, "cc");
        Person p4 = new Person(10, "dd");
    
        // ts容器最终只有一个元素p3 后续的三个元素p2 p1 p4都无法插入到容器中 因为element1.compareTo(element2)方法返回为0 时 element1是无法压入到TreeSet中
        ts.add(p3);
        ts.add(p2);
        ts.add(p1);
        ts.add(p4);
    
        // 输出 false,  false,  false
        // 即便p4和p1的hashCode() equals()返回值都是false p4依然无法压入ts中 因为p4.comareTo(p1) 返回值为0 
        System.out.println(p4.equals(p1) + ",  " + (p4.hashCode() == p1.hashCode()) + ",  " + (p4==p1));
        
        // 输出 set.size() = 1
        System.out.println("set.size() = " + ts.size());  
        
        // 输出10, cc
        for (Person person : ts) {
            System.out.println(person.getScore() + ", " + person.getName());
        }
    }
}

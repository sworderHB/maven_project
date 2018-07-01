package com.haobin.others;

import java.util.HashMap;
import java.util.Map;

public class M {
    public static void main(String[] args) throws Exception {
        //        A aa = new A();
        //        aa.f();  //同一个包内部，可以调用aa对象的protected方法
        //        
        //        B bb = new B();
        //        bb.f(); //同一个包内部，可以通过子类对象访问父类的protected方法

        //        String s1 = "ab";  
        //        String s2 = "abc";  
        //        String s3 = s1 + "c";           //s1是变量,s1与常量"c"相加  
        //        System.out.println(s3 == s2);   //输出false

        //        String s1 = "a" + "b" + "c";     //java中有常量优化机制,在编译时期就能确定s2的值为"abc",所以编译时期,在常量池中创建"abc"  
        //        String s2 = "abc";               //执行到这里时常量池中已经有了"abc",所以就不再创建,所以s1和s2指向的是常量池中同一个字符串常量"abc"  
        //        System.out.println(s1 == s2); 


        final String s1 = "ab";
        final String s2 = "abc";
        final String s3 = s1 + "c";
        System.out.println(s3 == s2);//true  
        System.out.println(s3.equals(s2));//true



        String str1 = new String("aa");
        String str2 = new String("aa");
        String str3 = "aa";
        System.out.println("str1 == str2 是" + (str1 == str2));
        System.out.println("str1.equals(str2) 是 " + str1.equals(str2));
        System.out.println("str1.hashCode() == str2.hashCode() 是"
                + (str1.hashCode() == str2.hashCode()));

        System.out.println("str1 == str3 是" + (str1 == str3));
        System.out.println("str1.equals(str3) 是 " + str1.equals(str3));
        System.out.println("str1.hashCode() == str3.hashCode() 是"
                + (str1.hashCode() == str3.hashCode()));


        A aa1 = new A();
        A aa2 = new A();
        System.out.println("aa1.hashCode() == aa2.hashCode() 是"
                + (aa1.hashCode() == aa2.hashCode()));
        
        Map m = new HashMap();
    }
}

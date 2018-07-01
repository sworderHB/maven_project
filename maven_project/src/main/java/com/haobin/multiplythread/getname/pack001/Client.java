package com.haobin.multiplythread.getname.pack001;

public class Client {
    public static void main(String[] args) {
        CountOperate c = new CountOperate();

        Thread t1 = new Thread(c);

        // c对象和t1对象都有name属性，name属性都来自Thread 方法
        // c对象继承自Thread所以有name属性
        // t1对象本身就是Thread对象，自然也有name属性
        t1.setName("TTT1");
        c.setName("哈哈");
        
        /**
         * 启动的是c对象的run()方法  或者说t1对象的run方法就是通过调用c对象的run()方法来实现的  不懂的话可以去看源码
         * 
         * Thread t1 = new Thread(c); 
         * 从源代码可知，就是把c注入到了t1对象的target属性中,
         * t1.run()内部调用的就是target.run()方法 
         */
        t1.start();
    }
}

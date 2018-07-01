package com.haobin.multiplythread.notifywait.pack001;

/*
 * 假设现在有T1 T2 T3 T4 四个线程
 * 
 * 在T4线程中调用了 aa.notify() 即便此时T1 T2 T3 没有一个线程因为wait aa对象而陷入阻塞状态， T4线程中执行aa.notify方法时也不会有任何错误
 * 本程序就证明了这一点
 */

public class TestNotify {
    public static void main(String[] args) {
        A aa = new A();
        Thread tt = new Thread(aa);
        tt.start();
        System.out.println("哈哈!");
    }
}


class A implements Runnable {
    public synchronized void run() //如果去掉了synchronized 则会报错，只有在同步代码块或同步方法中才可以使用notify方法
    {
        int cnt = 0;

        while (cnt < 20) {
            cnt++;
            System.out.println("AAAA" + cnt);
            try {
                //本程序可以正确运行，说明了：即便此时没有任何线程因为  wait this对象而陷入阻塞状态，调用notify方法也不会报错	
                this.notify(); 
            } catch (IllegalMonitorStateException e) {
                System.out.println("在非同步方法 非同不代码块中调用了notify方法");
                System.exit(-1);
            }
        }
    }
}



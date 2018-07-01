package com.haobin.multiplythread.locksupport.pack001;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class LockSupport002 {
    public static void main(String[] args) throws Exception {
        final ReentrantLock lock = new ReentrantLock();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " 线程启动运行了");
                try {
                    /**
                    * 这里18行代码不能省
                    * 否则会导致 40行在执行时t1线程可能还没有正常启动
                    * 即：如果注释掉了本行代码，运行结果就是t1线程会卡在 22行的位置无法结束
                    */
                    Thread.sleep(100);  //18

                    //park unpark完全可以不放在枷锁的代码中，即：  park() 、park(Object)、unpark(Thread)这三个方法可以放在同步代码中，也可以不放在同步代码块中
                    // 这里用锁只是为了保证若干行代码的互斥执行，目的是为了验证：线程先执行unpark，后执行park，则后执行的park()是不会陷入阻塞的
                    lock.lock();  //22
                    System.out.println(Thread.currentThread().getName() + " 线程获取到了lock锁");
                    //等待获取许可
                    LockSupport.park();
                    System.out.println("thread over." + Thread.currentThread().isInterrupted());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }


            }
        });
        t1.setName("t1");
        t1.start();

        //park unpark完全可以不放在枷锁的代码中，即：  park() 、park(Object)、unpark(Thread)这三个方法可以放在同步代码中，也可以不放在同步代码块中
        // 这里用锁只是为了保证若干行代码的互斥执行，目的是为了验证：线程先执行unpark，后执行park，则后执行的park()是不会陷入阻塞的
        lock.lock();
        System.out.println("main 线程获取到了lock锁");
        LockSupport.unpark(t1);  //40   如果执行本行代码时t1线程还没有正常启动，则本行代码的执行将不会释放许可
        System.out.println("main 线程结束了!");
        lock.unlock();
    }
}


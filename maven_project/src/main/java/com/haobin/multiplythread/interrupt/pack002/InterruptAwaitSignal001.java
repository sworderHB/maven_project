package com.haobin.multiplythread.interrupt.pack002;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 本程序证明了：condition.await(); 期间如果执行了interrupt()方法，condition.await()会抛出异常
 * 但是condition所处的Lock对象并不会释放锁
 */
public class InterruptAwaitSignal001 {
    private static ReentrantLock reentrantLock = new ReentrantLock();
    private static Condition condition = reentrantLock.newCondition();
    
    private static class R1 implements Runnable {
        @Override
        public void run() {
            System.out.println("1 " + Thread.currentThread().getName());
            reentrantLock.lock();  //16
            System.out.println("2 " + Thread.currentThread().getName());
            try {
                System.out.println("3 " + Thread.currentThread().getName());
                
                condition.await();  //23
                System.out.println("4 " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                System.out.println("5 " + Thread.currentThread().getName());
                
                // 如果把本行代码注释掉了，则会导致t2线程会卡在16行的位置无法获取reentrantLock锁
                // 因为t1线程虽然因为interrut()抛出了异常，但是锁是不会自动自动释放的。
                reentrantLock.unlock();
                
                System.out.println("6 " + Thread.currentThread().getName());
            }
        }
    }
    
    public static void main(String[] args) throws Exception{
        Runnable r1 = new R1();
        Thread t1 = new Thread(r1);
        t1.setName("t1");
        t1.start();
        Thread.sleep(2000);
        t1.interrupt();
        
        Thread.sleep(1000);
        
        Thread t2 = new Thread(r1);
        t2.setName("t2");
        t2.start();
        
        // 暂停1秒的目的是为了让t2 先停留在 23行位置， 这样主线程 53行代码先执行signal()，就可以唤醒t2线程了
        // 如果省略到本语句，则会导致t2线程停留在23行无法终止，因为主线程53行代码执行在了t2线程的前面
        Thread.sleep(1000);
        
        reentrantLock.lock();
        condition.signal(); //53
        reentrantLock.unlock();
    }
}

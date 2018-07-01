package com.haobin.multiplythread.interrupt.pack001;

/**
 * 当线程处于waiting状态时，调用interrupt方法，会抛InterruptedException异常， 线程中断，退出执行
 */
public class RunTest {
    public static void main(String[] args) {
        try {
            Object lock = new Object();
            ThreadA threadA = new ThreadA(lock);
            threadA.start();
            /* wait的线程调用interrupt，抛异常，停止线程，也是线程停止的一种方式 */
            Thread.sleep(1000);
            threadA.interrupt();
        } catch (InterruptedException ex) {
            // TODO: handle exception
            ex.printStackTrace();
        }
    }
}

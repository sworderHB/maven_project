package com.haobin.multiplythread.locksupport.pack001;

import java.util.concurrent.locks.LockSupport;

public class LockSupport001 {
    public static void main(String[] args) throws Exception {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //等待获取许可
                LockSupport.park();
                System.out.println("thread over." + Thread.currentThread().isInterrupted());
            }
        });

        t.start();
        Thread.sleep(100);
        // 中断线程
        t.interrupt();
        System.out.println("main over");
    }
}


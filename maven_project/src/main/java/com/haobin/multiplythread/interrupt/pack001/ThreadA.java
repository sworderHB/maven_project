package com.haobin.multiplythread.interrupt.pack001;

public class ThreadA extends Thread {
    private Object lock;

    public ThreadA(Object lock) {
        super();
        this.lock = lock;
    }

    public void serviceMethod(Object lock) {
        try {
            synchronized (lock) {
                System.out.println("ThreadName = " + Thread.currentThread().getName()
                        + " begin wait = " + System.currentTimeMillis());
                lock.wait();
//                while (true) {
//                    System.out.println("哈哈哈");
//                }
                
//                Thread.sleep(2000);
                
//                System.out.println("ThreadName = " + Thread.currentThread().getName()
//                        + " end wait = " + System.currentTimeMillis());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void run() {
       serviceMethod(lock);
    }
}

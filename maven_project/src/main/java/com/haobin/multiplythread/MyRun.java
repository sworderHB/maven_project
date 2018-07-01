package com.haobin.multiplythread;

public class MyRun {
    private String lock = new String("");

    private Runnable runnableA = new Runnable() {
        @Override
        public void run() {
            try {
                synchronized (lock) {
                    System.out.println("begin wait");
                    // 线程B先执行了lock.notify(); 本线程后执行lock.wait(); 这会导致本线程陷入一直等待下去  即14行代码永远都无法执行 
                    // 这就是notify()过早所带来的问题
                    lock.wait();
                    System.out.println("end wait"); //14
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private Runnable runnableB = new Runnable() {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("begin notify");
                lock.notify();
                System.out.println("end notify");
            }
        }
    };

    public static void main(String[] args) throws InterruptedException {
        MyRun run = new MyRun();

        Thread b = new Thread(run.runnableB);
        b.start();

        Thread.sleep(100);

        Thread a = new Thread(run.runnableA);
        a.start();
    }
}


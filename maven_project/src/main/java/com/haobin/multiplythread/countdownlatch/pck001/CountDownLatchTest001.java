package com.haobin.multiplythread.countdownlatch.pck001;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CountDownLatchTest001 {
    volatile private static int nextPrintWho = 1;
    volatile static CountDownLatch downLatch = new CountDownLatch(500 * 3);  
    
    private static ReentrantLock lock = new ReentrantLock();
    
    final private static Condition conditionA = lock.newCondition();
    final private static Condition conditionB = lock.newCondition();
    final private static Condition conditionC = lock.newCondition();

    public static void main(String[] args) throws Exception{
        long startTime = System.currentTimeMillis();
        
        Thread threadA = new Thread() {
            public void run() {
                try {
                    lock.lock();
                    while (nextPrintWho != 1) {
                        conditionA.await();
                    }
                    for (int i = 0; i < 3; i++) {
                        System.out.println("ThreadA " + (i + 1));
                    }
                    nextPrintWho = 2;
                    conditionB.signalAll();
                    System.out.println("ThreadA 继续执行!");
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    downLatch.countDown();
                    lock.unlock();
                }
            }
        };

        Thread threadB = new Thread() {
            public void run() {
                try {
                    lock.lock();
                    while (nextPrintWho != 2) {
                        conditionB.await();
                    }
                    for (int i = 0; i < 3; i++) {
                        System.out.println("ThreadB " + (i + 1));
                    }
                    nextPrintWho = 3;
                    conditionC.signalAll();
                    System.out.println("ThreadB 继续执行!");
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    downLatch.countDown();
                    lock.unlock();
                }
            }
        };

        Thread threadC = new Thread() {
            public void run() {
                try {
                    lock.lock();
                    while (nextPrintWho != 3) {
                        conditionC.await();
                    }
                    for (int i = 0; i < 3; i++) {
                        System.out.println("ThreadC " + (i + 1));
                    }
                    nextPrintWho = 1;
                    conditionA.signalAll();
                    System.out.println("ThreadC 继续执行!");
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    downLatch.countDown();
                    lock.unlock();
                }
            }
        };
        
        Thread[] aArray = new Thread[500];
        Thread[] bArray = new Thread[500];
        Thread[] cArray = new Thread[500];

        for (int i = 0; i < 500; i++) {
            aArray[i] = new Thread(threadA);
            bArray[i] = new Thread(threadB);
            cArray[i] = new Thread(threadC);

            aArray[i].start();
            bArray[i].start();
            cArray[i].start();
        }
        
        downLatch.await();
        long endTime = System.currentTimeMillis();
        System.out.println("总执行时间是: " + (endTime-startTime));
    }
}


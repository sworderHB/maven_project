package com.haobin.multiplythread.locks.pack001;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class T1 {
 private static Lock lock = new ReentrantLock();
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    // 读锁   注意：这里的readLock和writeLock是互斥的
    private static Lock readLock = readWriteLock.readLock();
    // 写锁   注意：这里的readLock和writeLock是互斥的
    private static Lock writeLock = readWriteLock.writeLock();

    public static void main(String[] args) {
        readLock.newCondition().signal();
    }
}

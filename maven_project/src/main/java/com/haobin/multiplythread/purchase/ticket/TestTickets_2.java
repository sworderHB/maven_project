package com.haobin.multiplythread.purchase.ticket;

class A1 implements Runnable {
    public int tickets = 100;
    String str = new String("哈哈");

    public void run() {
        while (true) {
            synchronized (str) {
                if (tickets > 0) {
                    System.out.printf("%s线程正在卖出第%d张票\n",
                            Thread.currentThread().getName(), tickets);
                    --tickets;
                } else {
                    break;
                }
            }
        }
    }
}


/*
 * 2009年6月22日9:28:08 正确的程序
 */

public class TestTickets_2 {


    public static void main(String[] args) {
        A1 aa = new A1();
        Thread t1 = new Thread(aa);
        t1.start();

        Thread t2 = new Thread(aa);
        t2.start();
    }
}

package com.haobin.multiplythread.purchase.ticket;
/*
 * 用创建线程的第一种方式 来买票 本程序运行OK
 */

class A2 extends Thread {
    public static int tickets = 100; //static不能省
    public static String str = new String("哈哈"); //static不能省

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


public class TestTickets_3 {
    public static void main(String[] args) {
        A2 aa1 = new A2();
        aa1.start();

        A2 aa2 = new A2();
        aa2.start();
    }
}

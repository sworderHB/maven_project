package com.haobin.multiplythread.produceconsumer.pack001;

public class Producer implements Runnable {
    SyncStack ss = null;

    public Producer(SyncStack ss) {
        this.ss = ss;
    }

    public void run() {
        char ch;

        // 总共生产20个产品
        for (int i = 0; i < 20; ++i) {
            ch = (char) ('a' + i);
            ss.push(ch);
            //            try {
            //                Thread.sleep(500);
            //            } catch (Exception e) {
            //            }
        }
    }
}

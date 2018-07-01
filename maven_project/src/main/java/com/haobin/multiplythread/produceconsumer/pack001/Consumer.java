package com.haobin.multiplythread.produceconsumer.pack001;

public class Consumer implements Runnable {
    SyncStack ss = null;

    public Consumer(SyncStack ss) {
        this.ss = ss;
    }

    // 总共消费20个产品
    public void run() {
        for (int i = 0; i < 20; ++i) {
            ss.pop();
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
            }
        }
    }
}

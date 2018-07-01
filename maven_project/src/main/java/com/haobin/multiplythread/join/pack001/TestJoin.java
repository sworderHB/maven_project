package com.haobin.multiplythread.join.pack001;

public class TestJoin {	
	public static void main(String args[]){
		MyRunner r = new MyRunner();
		Thread threadObject001 = new Thread(r);
		threadObject001.start();
		try{
		    /**
		     * 暂停当前正在执行threadObject001.join();的线程，
		     * 直到threadObject001所对应的线程运行终止之后，
		     * 当前线程才会获得继续执行的机会
		     */
			threadObject001.join();   // 7行
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		// 经测试发现，程序最终运行结果是： 先输出1000个 "子线程。。。。。" ，然后才是输出这里的 "主线程。。。。。。"
		for(int i=0;i<1000;i++){
			System.out.println("主线程:" + i);
		}
    }
}

class MyRunner implements Runnable {
	public void run() {
		for(int i=0;i<1000;i++) {
			System.out.println("子线程: " + i);  //29
		}
	}
}

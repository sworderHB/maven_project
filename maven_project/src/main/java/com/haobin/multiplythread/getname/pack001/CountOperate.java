package com.haobin.multiplythread.getname.pack001;

public class CountOperate extends Thread {
	public CountOperate() {
		/**
		 * 每个子类构造方法的第一条语句，都是隐含地调用super()，如果父类没有这种形式的构造函数，那么在编译的时候就会报错
		 * 因此这里隐含的调用了 Thread.Thread()构造方法，  可以在java.lang.Thread.Thread() 方法中加断点验证
		 * 
		 * 该方法会把CountOperate对象从Thread继承过来的name属性值设置为 "Thread-" + nextThreadNum()  注意nextThreadNum()的返回值从0开始，没调用一次值就加1
		 */
		System.out.println("CountOperate---begin");
		System.out.println("11 Thread.currentThread().getName()=" + Thread.currentThread().getName());  //主线程 main
		System.out.println("12 this.getName()=" + this.getName());    //这里的this当然值得是c对象  Thread-0
		System.out.println("13 CountOperate---end");
	}

	@Override
	public void run() {
		System.out.println("run---begin");
		
		// Thread.currentThread() 和 this 不是同一个对象  
		// 以本程序为例 这里的 Thread.currentThread()代表的是t1,  this代表的是c    
		System.out.println("22 Thread.currentThread().getName()=" + Thread.currentThread().getName()); // TTT1
		
		System.out.println("23 this.getName()=" + this.getName());  // 哈哈
		
		System.out.println("24 run---end");
	}
}

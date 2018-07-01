package com.haobin.multiplythread.threadlocal.pack001;

public class ThreadLocalExt {
	public static ThreadLocal tl = new ThreadLocal() {
		// 如果把6-8行注释掉了，则16-17行都输出  “我的值"”
		protected Object initialValue() { //6
			return "aaa";
		} //8
	};

	public static void main(String[] args) {
		if (tl.get() == null) {
			tl.set("bbb");
		}
		System.out.println(tl.get());  //16
		System.out.println(tl.get()); //17
	}
}

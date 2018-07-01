package com.haobin.extend;


class A{
	public static void f(int i) {
		System.out.println("A类的 " + i);
	}
	
	
	public void f(){  // 10
		
	}
}

class B extends A {
//	这里添加@Override 编译会报错, 即：	static方法不能被重写
//	@Override
	public static void f(int i) {
		System.out.println("B类的 " + i);
	}
	
//	如果把10行的public改为private，这里编译就会报错， 即: private方法不能被重写
	@Override
	public void f(){
	}
}

public class StasticWriteTest001 {
	public static void main(String[] args) {
	}
}

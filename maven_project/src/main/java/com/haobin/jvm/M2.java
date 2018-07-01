package com.haobin.jvm;

class A1{
	int i = 1;
	
	public A1() {
		this.i = 2;
	}
}

public class M2 {
	public static void main(String[] args) {
		A1 a1 = new A1();
		System.out.println(a1.i);  //输出结果是2
	}
}

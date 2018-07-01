package com.haobin.jvm;

class A{
}

public class SingleAInstance {
	private static class SingleA{
		private static final A aa = new A();
	}
	
	private SingleAInstance() {
	}

	public static final A getSingleAInstance() {
		return SingleA.aa;
	}
}

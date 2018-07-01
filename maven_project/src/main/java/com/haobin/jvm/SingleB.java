package com.haobin.jvm;

class B{
}

public class SingleB {
	public static final B getBInstance() {
		return BInstance.bb;
	}
	
	private SingleB() {
	}
	
	private static class  BInstance {
		private final static B bb = new B(); 
	}
}

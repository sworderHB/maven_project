package com.haobin.extend;

import java.util.concurrent.ConcurrentHashMap;

class Father {
	public static void staticMethod() {
		System.out.println("Father");
	}
}

class Son extends Father {
//	@Override  因为从逻辑上达不到重写的目的，所以这里添加 @Override 会编译报错
	public static void staticMethod() {
		System.out.println("Son");
	}
}

public class M {
	public static void main(String[] args) {
//		Father mByFather = new Father();
//		Father mBySon = new Son();
//		Son son = new Son();
//
//		mByFather.staticMethod();
//		mBySon.staticMethod();  // 这里返回的是Father而不是Son， static方法上重写不会报错，但是从逻辑运行效果上来看达不到多态的目的
//		son.staticMethod();
	
		ConcurrentHashMap<String, Integer> m = new ConcurrentHashMap<String, Integer>();
//		m.put(null, null);
		m.put("aa", null);
		m.put("bb", 2);
//		m.put(null, 1);
		
		System.out.println( m.get("bb") );
		
		
	}
}
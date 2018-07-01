package com.haobin.deep.copy.test001;

class Professor0 implements Cloneable {
	String name;
	int age;

	Professor0(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

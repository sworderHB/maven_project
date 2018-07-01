package com.haobin.reflect;

import java.util.List;
import java.util.Map;

public class People {
	private String name;
	private String password;
	
	private String address;
	
	private Integer age;

	private Map<String, String> map1;

	private List<String> list1;

	public People() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	
	
	public Map<String, String> getMap1() {
		return map1;
	}

	public void setMap1(Map<String, String> map1) {
		this.map1 = map1;
	}

	public List<String> getList1() {
		return list1;
	}

	public void setList1(List<String> list1) {
		this.list1 = list1;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "People [name=" + name + ", password=" + password + ", address=" + address + ", age=" + age + ", map1=" + map1
				+ ", list1=" + list1 + "]";
	}
}

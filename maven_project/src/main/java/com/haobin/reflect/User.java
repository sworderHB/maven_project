package com.haobin.reflect;

import java.util.List;
import java.util.Map;

public class User {
	private String name;
	private String password;
	
	private String address;
	
	private Integer rank;

	private Map<String, String> map1;

	private List<String> list1;

	public User() {
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

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", address=" + address + ", rank=" + rank + ", map1=" + map1
				+ ", list1=" + list1 + "]";
	}

	
}

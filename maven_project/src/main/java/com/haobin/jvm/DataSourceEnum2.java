package com.haobin.jvm;

public enum DataSourceEnum2 {
	/** 余额对账 */
	BALANCE_RECON33333("BALANCE_RECON2");
	
	private String value;
	
	// 这里只能写private 不能写成public 枚举类型的构造方法默认都是private的且只能是private的
	private DataSourceEnum2(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}

package com.haobin.jvm;

public class M3 {
	public static void main(String[] args) {
		DataSourceEnum2 dataSourceEnum = DataSourceEnum2.BALANCE_RECON33333;
		System.out.println(dataSourceEnum + ",  " + dataSourceEnum.getValue());
		
//		for(DataSourceEnum2 m : DataSourceEnum2.values()) { 
//	        System.out.println(m.getValue());
//	     }
		
		System.out.println( DataSourceEnum2.valueOf("BALANCE_RECON33333") );
	}
}

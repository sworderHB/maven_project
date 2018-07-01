package com.haobin.annotation;

import java.lang.reflect.Field;
public class Test001 {
	public static void main(String[] args) throws Exception{
		for(Field f:UserPojo.class.getDeclaredFields()){
			System.out.println(f.getName()+" "+f.isAnnotationPresent(IdAnnation.class) );
		}
	}
}


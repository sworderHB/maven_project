package com.haobin.annotation002;

import java.lang.reflect.Field;

public class Test2 {
	public static void main(String[] args) throws Exception {
		for (Field field : UserPojo2.class.getDeclaredFields()) {
			System.out.println(field.getName() + " " + field.isAnnotationPresent(IdAnnation2.class));
			if (field.isAnnotationPresent(IdAnnation2.class)) {
				System.out.println(((IdAnnation2) field.getAnnotation(IdAnnation2.class)).type());
			}
		}
	}
}

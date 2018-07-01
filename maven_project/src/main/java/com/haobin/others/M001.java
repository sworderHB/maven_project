package com.haobin.others;

import java.util.Random;

public class M001 {
	public static void main(String[] args) {
//		double x = 1 / 2;
//		System.out.println(x);
		Random r = new Random();
		for (int i=0; i<10; ++i) {
			System.out.println(r.nextInt());
		}
	}
}

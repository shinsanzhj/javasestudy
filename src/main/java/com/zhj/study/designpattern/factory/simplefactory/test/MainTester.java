package com.zhj.study.designpattern.factory.simplefactory.test;

import com.zhj.study.designpattern.factory.IProduct;
import com.zhj.study.designpattern.factory.simplefactory.SimpleFactory;

public class MainTester {
	
	private static SimpleFactory factory;
	private static IProduct product;

	public static void main(String[] args) {
//		step1();
		System.out.println((2 << 4) + 2);
	}

	private static void step1() {
		factory = new SimpleFactory();
		product = factory.getProduct(1);
		product.setPrice(10);
		
		product = factory.getProduct(2);
		product.setPrice(20);
		
		product = factory.getProduct(3);
		product.setPrice(30);
	}
}

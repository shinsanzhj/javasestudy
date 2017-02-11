package com.zhj.study.designpattern.factory.nonfactory.test;

import com.zhj.study.designpattern.factory.IProduct;
import com.zhj.study.designpattern.factory.Product1;
import com.zhj.study.designpattern.factory.Product2;
import com.zhj.study.designpattern.factory.Product3;

public class MainTester {
	
	private static Product1 product1;
	private static Product2 product2;
	private static Product3 product3;
	
	private static IProduct product;

	public static void main(String[] args) {
//		step1();
		step2();
	}

	// 类中需要保存具体实现的引用
	private static void step1() {
		product1 = new Product1();
		product1.setPrice(10);
		
		product2 = new Product2();
		product2.setPrice(20);
		
		product3 = new Product3();
		product3.setPrice(30);
	}
	
	// 类中只需保存一个接口引用
	private static void step2() {
		product = new Product1();
		product.setPrice(10);
		
		product = new Product2();
		product.setPrice(20);
		
		product = new Product3();
		product.setPrice(30);
	}
}

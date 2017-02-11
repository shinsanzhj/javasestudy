package com.zhj.study.designpattern.factory.ifactory.test;

import com.zhj.study.designpattern.factory.IProduct;
import com.zhj.study.designpattern.factory.IProductFactory;
import com.zhj.study.designpattern.factory.ifactory.Product1Factory;
import com.zhj.study.designpattern.factory.ifactory.Product2Factory;
import com.zhj.study.designpattern.factory.ifactory.Product3Factory;

public class MainTester {
	
	private static IProductFactory factory;
	private static IProduct product;

	public static void main(String[] args) {
		step1();
	}

	private static void step1() {
		factory = new Product1Factory();
		product = factory.getProduct();
		product.setPrice(10);
		
		factory = new Product2Factory();
		product = factory.getProduct();
		product.setPrice(10);
		
		factory = new Product3Factory();
		product = factory.getProduct();
		product.setPrice(10);
	}
}

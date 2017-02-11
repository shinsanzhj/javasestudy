package com.zhj.study.designpattern.factory;

public class Product2 implements IProduct {

	@Override
	public void setPrice(int price) {
		System.out.println("为2号产品设置价格：" + price);
	}

}

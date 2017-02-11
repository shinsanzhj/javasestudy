package com.zhj.study.designpattern.factory;

public class Product3 implements IProduct {

	@Override
	public void setPrice(int price) {
		System.out.println("为3号产品设置价格：" + price);
	}

}

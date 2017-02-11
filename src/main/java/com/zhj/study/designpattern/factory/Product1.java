package com.zhj.study.designpattern.factory;

public class Product1 implements IProduct {

	@Override
	public void setPrice(int price) {
		System.out.println("为1号产品设置价格：" + price);
	}

}

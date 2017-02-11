package com.zhj.study.designpattern.factory.ifactory;

import com.zhj.study.designpattern.factory.IProduct;
import com.zhj.study.designpattern.factory.IProductFactory;
import com.zhj.study.designpattern.factory.Product2;

public class Product2Factory implements IProductFactory {

	@Override
	public IProduct getProduct() {
		return new Product2();
	}
}

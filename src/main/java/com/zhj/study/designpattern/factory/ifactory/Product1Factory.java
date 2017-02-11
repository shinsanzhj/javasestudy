package com.zhj.study.designpattern.factory.ifactory;

import com.zhj.study.designpattern.factory.IProduct;
import com.zhj.study.designpattern.factory.IProductFactory;
import com.zhj.study.designpattern.factory.Product1;

public class Product1Factory implements IProductFactory {

	@Override
	public IProduct getProduct() {
		return new Product1();
	}
}

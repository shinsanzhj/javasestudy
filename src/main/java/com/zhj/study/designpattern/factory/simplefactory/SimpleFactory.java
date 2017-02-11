package com.zhj.study.designpattern.factory.simplefactory;

import com.zhj.study.designpattern.factory.IProduct;
import com.zhj.study.designpattern.factory.Product1;
import com.zhj.study.designpattern.factory.Product2;
import com.zhj.study.designpattern.factory.Product3;


public class SimpleFactory {

	// 缺点：
	// 		如果新增一个产品4，那么这个类就要修改代码
	// 		这个工厂依赖了所有的产品
	public IProduct getProduct(int type) {
		IProduct product = null;
		switch(type) {
		case 1: 
			product = new Product1();
			break;
		case 2:
			product = new Product2();
			break;
		case 3:
			product = new Product3();
			break;
		default:
			break;
		}
		return product;
	}
}

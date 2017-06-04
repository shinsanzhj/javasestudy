package com.zhj.study.designpattern.proxy.cglib.advance.lazyloader;

import net.sf.cglib.proxy.LazyLoader;

public class ConcreteLazyLoader implements LazyLoader {

	@Override
	public Object loadObject() throws Exception {
		System.out.println("开始构造之前延迟加载的对象,方式:LazyLoader");
		PropertyBean propertyBean = new PropertyBean();
		propertyBean.setKey("age");
		propertyBean.setObject("28");
		return propertyBean;
	}

}

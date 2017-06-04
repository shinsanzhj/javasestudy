package com.zhj.study.designpattern.proxy.cglib.advance.lazyloader;

import net.sf.cglib.proxy.Dispatcher;

public class ConcreteLazyDispatcher implements Dispatcher {

	@Override
	public Object loadObject() throws Exception {
		System.out.println("开始构造之前延迟加载的对象,方式:Dispatcher");
		PropertyBean propertyBean = new PropertyBean();
		propertyBean.setKey("nickName");
		propertyBean.setObject("shinsanzhj");
		return propertyBean;
	}

}

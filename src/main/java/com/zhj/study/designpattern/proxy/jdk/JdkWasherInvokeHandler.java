package com.zhj.study.designpattern.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.zhj.study.designpattern.proxy.Washer;

public class JdkWasherInvokeHandler implements InvocationHandler {

	private Washer delegatedObject;
	
	public JdkWasherInvokeHandler() {
		// TODO Auto-generated constructor stub
	}
	
	public JdkWasherInvokeHandler(Washer washer) {
		this.delegatedObject = washer;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("JDK代理方式：" + method.getName());
		return method.invoke(delegatedObject, args);
	}

}

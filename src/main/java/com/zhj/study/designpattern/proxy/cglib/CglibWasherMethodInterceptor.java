package com.zhj.study.designpattern.proxy.cglib;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibWasherMethodInterceptor implements MethodInterceptor {

	@Override
	public Object intercept(Object proxy, Method method, Object[] args,
			MethodProxy methodProxy) throws Throwable {
		System.out.println("Cglib代理:" + method.getName());
		return methodProxy.invokeSuper(proxy, args);
	}
	
	
}

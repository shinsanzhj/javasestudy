package com.zhj.study.designpattern.proxy;

import java.lang.reflect.Proxy;

import net.sf.cglib.proxy.Enhancer;

import com.zhj.study.designpattern.proxy.cglib.CglibWasherMethodInterceptor;
import com.zhj.study.designpattern.proxy.jdk.JdkWasherInvokeHandler;
import com.zhj.study.designpattern.proxy.normal.NormalWasherProxy;

public class MainTester {

	/**
	 * @param args
	 *
	 * <pre>
	 * 修改日期		修改人	修改原因
	 * 2017-3-21	钟华杰	新建
	 * </pre>
	 */
	public static void main(String[] args) {
//		testNormalProxy();
//		testJdkProxy();
		testCglibProxy();
	}

	
	private static void testNormalProxy() {
		Washer washer = new NormalWasherProxy();
		washer.autoWash();
	}
	
	private static void testJdkProxy() {
		JdkWasherInvokeHandler invokeHandler = new JdkWasherInvokeHandler(new HaierWasher());
		Washer washer = (Washer) Proxy.newProxyInstance(Washer.class.getClassLoader(), HaierWasher.class.getInterfaces(), invokeHandler);
		washer.autoWash();
	}
	
	private static void testCglibProxy() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(HaierWasher.class);
		enhancer.setCallback(new CglibWasherMethodInterceptor());
		Washer washer = (Washer) enhancer.create();
		washer.autoWash();
	}
}

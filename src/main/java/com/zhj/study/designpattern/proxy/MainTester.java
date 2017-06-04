package com.zhj.study.designpattern.proxy;

import java.lang.reflect.Proxy;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;

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
	public static void main(String[] args) throws Exception {
//		testNormalProxy();
//		testJdkProxy();
//		testCglibProxy();
		testJavassistProxy();
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
	
	private static void testJavassistProxy() throws Exception {
		CtClass ctClass = ClassPool.getDefault().get("com.zhj.study.designpattern.proxy.HaierWasher");
		String oldMethodName = "setWashStyle";
		CtMethod oldMethod = ctClass.getDeclaredMethod(oldMethodName);
		String newName = oldMethodName + "$impl";
		oldMethod.setName(newName);
		CtMethod newMethod = CtNewMethod.copy(oldMethod, oldMethodName, ctClass, null);
		StringBuffer sb = new StringBuffer();
		sb.append("{")
			.append("System.out.println(\"哈哈哈哈\");")
			.append(oldMethodName + "($$);")
			.append("System.out.println(\"结束调用\");")
			.append("}");
		newMethod.setBody(sb.toString());
		ctClass.addMethod(newMethod);
		Washer washer = (Washer) ctClass.toClass().newInstance();
		washer.setWashStyle("ssxx");
	}
}

package com.zhj.study.javase.classloader;

import com.zhj.study.javase.classloader.material.Demo;
import com.zhj.study.testclassnofund.DemoStatic;

public class MainTester {

	/**
	 * @param args
	 *
	 * <pre>
	 * 修改日期		修改人	修改原因
	 * 2016-11-16	钟华杰	新建
	 * </pre>
	 */
	public static void main(String[] args) {
//		showEvn();
//		showAllClassLoader();
//		testClassStatic();
//		testClassForName();
		
		com.zhj.study.testclassnofund.Demo demo = new com.zhj.study.testclassnofund.Demo();
//		demo.useEhcacheClass();
		
		DemoStatic demo2 = new DemoStatic();
	}
	
	//环境变量
	private static void showEvn() {
		System.out.println("java.ext.dirs:" + System.getProperty("java.ext.dirs"));
		System.out.println("java.class.path:" + System.getProperty("java.class.path"));
		System.out.println();
	}
	
	//显示所有的类加载器
	private static void showAllClassLoader() {
		//自定义类
		System.out.println(Demo.class.getClassLoader());
		System.out.println(Demo.class.getClassLoader().getParent());
		System.out.println(Demo.class.getClassLoader().getParent().getParent());
		//Java自带类
		System.out.println(String.class.getClassLoader());
		//Java ext类，在C:\Windows\Sun\Java\lib\ext\dnsns.jar中
		try {
			System.out.println(Class.forName("sun.net.spi.nameservice.dns.DNSNameService").getClassLoader());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//测试类加载顺序涉及内容
	private static void testClassStatic() {
		// 这个value其实是DemoParent的静态变量，执行下面这句，Demo中的静态代码块是不会被执行的
		System.out.println(Demo.value);
		System.out.println("MainTester输出");
		System.out.println(Demo.initCount);
		System.out.println(Demo.name);
		// 会执行实例代码块
		Demo demo = new Demo();
		Demo demo2 = new Demo();
	}
	
	//测试Class.forName()相关内容
	private static void testClassForName() {
		// true：初始化静态变量、执行静态代码块
		// false：静态变量不会被初始化、静态代码块不会被执行
		try {
			Class.forName("com.zhj.study.javase.classloader.material.Demo", true, MainTester.class.getClassLoader());
			// 即使都为true，也不会执行两次静态代码块
			Class.forName("com.zhj.study.javase.classloader.material.Demo", true, MainTester.class.getClassLoader());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}

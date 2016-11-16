package com.zhj.study.javase.classloader;

import com.zhj.study.javase.classloader.material.Demo;

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
		showEvn();
		showAllClassLoader();
	}
	
	private static void showEvn() {
		System.out.println("java.ext.dirs:" + System.getProperty("java.ext.dirs"));
		System.out.println("java.class.path:" + System.getProperty("java.class.path"));
		System.out.println();
	}
	
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

}

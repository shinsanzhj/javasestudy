package com.zhj.study.javase.classloader.material;


public class Demo extends DemoParent {

	private static final int maxcount = 100;
	public static String name = staticDemoMethod();
	public static final int initCount;
//	public static String value = "Demo_Value";
	
	private String id;
	
	public Demo() {
		System.out.println("调用Demo的无参构造函数");
	}
	
	static {
//		System.out.println("before~initCount:" + initCount);
		System.out.println("执行了Demo的静态代码块");
		initCount = 3;
		name = "DEMO_BB";
//		System.out.println("after~initCount:" + initCount);
	}
	
	{
		System.out.println("执行了Demo的实例代码块");
	}
	
	public static String staticDemoMethod() {
		System.out.println("执行了staticDemoMethod方法");
		name = "DEMO_AA";
		return name;
	}
}

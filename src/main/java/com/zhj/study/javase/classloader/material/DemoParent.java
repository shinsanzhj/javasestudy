package com.zhj.study.javase.classloader.material;

public class DemoParent {

	private static String id = "ID";
	protected String familyName = "zhong";
	public static String value = "Parent_Value";
	
	public DemoParent() {
		System.out.println("调用了DemoParent的无参构造方法");
	}
	
	static {
		System.out.println("执行了DemoParent的静态代码块");
	}
	
	{
		System.out.println("执行了DemoParent的实例代码块");
	}
}

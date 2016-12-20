package com.zhj.study.javase.memery.material;

/**
 * 用来研究对象创建时的一些细节问题
 * @author zhj
 *
 */
public class ObjectCreateDetail {

	private static String NAME = getName("1");
	
	static {
		NAME = getName("2");
	}
	
	private static String getName(String id) {
		System.out.println(NAME);
		NAME = "[id:" + id + "]";
		return NAME;
	}
	
	public static void print() {
		System.out.println("加载了ObjectCreateDetail类");
	}
}

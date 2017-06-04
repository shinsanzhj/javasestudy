package com.zhj.study.javase.memery.gc.finalize;

public class Test1 {

	private static long instanceCreatedCount = 0;
	private static long instanceDeletedCount = 0;
	
	public Test1() {
		instanceCreatedCount++;
	}
	
	// -Xmx32m -verbose:gc
	public static void main(String[] args) {
		for(int i = 0; ; i++) {
			Test1 obj = new Test1();
			obj = null;
			if(i % 10000000 == 0) {
				System.out.println("创建对象个数：" + instanceCreatedCount + "，删除对象个数：" + instanceDeletedCount + "，存活对象个数：" + (instanceCreatedCount - instanceDeletedCount));
			}
		}
	}
}

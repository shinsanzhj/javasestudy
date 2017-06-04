package com.zhj.study.javase.memery.gc.finalize;

/**
 * 和Test不一样的地方：重写了finalize方法
 * 会报OOM异常
 * 原因：
 * 		1.重写了finalize方法，就是一个finalizable对象，创建该对象时，还会创建一个Finalizer对象，指向该对象。并且把Finalizer对象添加到Finalizer引用队列中。
 * 		2.Finalize守护线程的优先级比较低，所以处理该队列的速度跟不上对象创建的速度。
 * 解决：
 * 		1.不要重写finalize方法
 * 		2.提高Finalize守护线程的优先级。不过好像没有对应的API直接找到Finalize线程，只能在所有线程中通过名称来查找。
 * 引用：
 * 		http://www.fasterj.com/articles/finalizer1.shtml
 * @author zhj
 *
 */
public class Test2 {

	private static long instanceCreatedCount = 0;
	private static long instanceDeletedCount = 0;
	
	public Test2() {
		instanceCreatedCount++;
	}
	
	// -Xmx32m -verbose:gc
	public static void main(String[] args) {
		for(int i = 0; ; i++) {
			Test2 obj = new Test2();
			obj = null;
			if(i % 10000 == 0) {
				System.out.println("创建对象个数：" + instanceCreatedCount + "，删除对象个数：" + instanceDeletedCount + "，存活对象个数：" + (instanceCreatedCount - instanceDeletedCount));
			}
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		instanceDeletedCount++;
	}
}

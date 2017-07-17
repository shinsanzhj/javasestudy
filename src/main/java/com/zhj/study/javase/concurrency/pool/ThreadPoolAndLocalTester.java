package com.zhj.study.javase.concurrency.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocal和线程池配合使用时，要记得把ThreadLocal中的内容清除，否则会出现变量串用的bug
 * 
 *
 * <pre>
 * 修改日期		修改人	修改原因
 * 2017-7-17	钟华杰	新建
 * </pre>
 */
public class ThreadPoolAndLocalTester {

	private static final ThreadLocal<Integer> userAccessTimes = new ThreadLocal<Integer>();
	
	/**
	 * @param args
	 *
	 * <pre>
	 * 修改日期		修改人	修改原因
	 * 2017-7-17	钟华杰	新建
	 * </pre>
	 */
	public static void main(String[] args) {
		ThreadLocalTask task = new ThreadLocalTask();
		// 只包含3条线程的线程池
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
		// 运行7次任务
		fixedThreadPool.execute(task);
		fixedThreadPool.execute(task);
		fixedThreadPool.execute(task);
		fixedThreadPool.execute(task);
		fixedThreadPool.execute(task);
		fixedThreadPool.execute(task);
		fixedThreadPool.execute(task);
	}
	
	private static class ThreadLocalTask implements Runnable {
		@Override
		public void run() {
			// 重要：防止线程池中的线程本地变量串用的情况
			userAccessTimes.remove();
			System.out.println("线程[" + Thread.currentThread().getName() + "]开始执行，userAccessTimes：" + userAccessTimes.get());
			if(null == userAccessTimes.get()) {
				userAccessTimes.set(1);
			} else {
				userAccessTimes.set(userAccessTimes.get() + 1);
			}
			System.out.println("线程[" + Thread.currentThread().getName() + "]执行完毕，userAccessTimes：" + userAccessTimes.get());
		}
	}

}

package com.zhj.study.javase.concurrency.atomic;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanTester {

	private static AtomicBoolean hasInited = new AtomicBoolean(false);
	private static AtomicBoolean wakeupPending = new AtomicBoolean(false);
	
	
	/**
	 * @param args
	 *
	 * <pre>
	 * 修改日期		修改人	修改原因
	 * 2017-7-17	钟华杰	新建
	 * </pre>
	 */
	public static void main(String[] args) {
		Executor executor = Executors.newFixedThreadPool(3);
		AtomicBooleanTask task = new AtomicBooleanTask();
		executor.execute(task);
		executor.execute(task);
		executor.execute(task);
	}

	private static class AtomicBooleanTask implements Runnable {
		@Override
		public void run() {
			if(hasInited.compareAndSet(false, true)) {
				System.out.println("线程[" + Thread.currentThread().getName() + "]进行初始化操作...");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("线程[" + Thread.currentThread().getName() + "]执行后续逻辑...");
			
			
			while(true) {
				if(wakeupPending.compareAndSet(false, true)) {
					System.out.println("线程[" + Thread.currentThread().getName() + "]执行while中的逻辑...");					
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					wakeupPending.set(false);
				}
				System.out.println("\t\t线程[" + Thread.currentThread().getName() + "]执行while后面的逻辑...");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

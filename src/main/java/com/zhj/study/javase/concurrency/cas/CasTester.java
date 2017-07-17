package com.zhj.study.javase.concurrency.cas;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CasTester {

	private static int sum = 0;
	
	/**
	 * @param args
	 *
	 * <pre>
	 * 修改日期		修改人	修改原因
	 * 2017-7-17	钟华杰	新建
	 * </pre>
	 */
	public static void main(String[] args) {
		SpinLock lock = new SpinLock();
		CasTask casTask = new CasTask(lock);
		Executor executor = Executors.newFixedThreadPool(3);
		executor.execute(casTask);
		executor.execute(casTask);
		executor.execute(casTask);
	}

	private static class CasTask implements Runnable {
		private SpinLock lock;
		
		public CasTask(SpinLock lock) {
			this.lock = lock;
		}
		
		@Override
		public void run() {
			this.lock.lock();
			this.lock.lock();
			System.out.println("线程[" + Thread.currentThread().getName() + "]执行lock中的逻辑...");
			sum++;
			System.out.println("sum:" + sum);
			this.lock.unlock();
			this.lock.unlock();
		}
	}
}

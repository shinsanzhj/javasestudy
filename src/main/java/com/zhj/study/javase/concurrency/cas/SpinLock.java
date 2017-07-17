package com.zhj.study.javase.concurrency.cas;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 实现自旋锁
 * 
 *
 * <pre>
 * 修改日期		修改人	修改原因
 * 2017-7-17	钟华杰	新建
 * </pre>
 */
public class SpinLock {
	
	AtomicReference<Thread> owner = new AtomicReference<Thread>();
	private int count;

	public void lock() {
		if(owner.get() == Thread.currentThread()) {
			System.out.println("线程[" + Thread.currentThread().getName() + "]再次获取了锁...");
			count++;
			return;
		}
		
		while(!owner.compareAndSet(null, Thread.currentThread())) {
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			System.out.println("\t\t线程[" + Thread.currentThread().getName() + "]正在自旋...");
		}
		System.out.println("线程[" + Thread.currentThread().getName() + "]获得了锁...");
	}
	
	public void unlock() {
		if(owner.get() == Thread.currentThread()) {
			if(count > 0) {
				count--;
			} else {
				owner.compareAndSet(Thread.currentThread(), null);
			}
		}
	}
}

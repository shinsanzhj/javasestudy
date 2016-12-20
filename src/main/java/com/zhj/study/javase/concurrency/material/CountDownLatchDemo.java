package com.zhj.study.javase.concurrency.material;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

	public static class Task implements Runnable {
		private CountDownLatch countDownLatch;
		
		public Task(CountDownLatch countDownLatch) {
			this.countDownLatch = countDownLatch;
		}
		
		public void run() {
			try {
				System.out.println("线程：【" + Thread.currentThread().getName() + "】开始执行任务");
				Thread.sleep(5000);
			} catch(Exception e) {
				
			} finally {
				countDownLatch.countDown();
			}
		}
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(2);
		Task task = new Task(countDownLatch);
		Thread worker1 = new Thread(task);
		Thread worker2 = new Thread(task);
		long startTime = System.currentTimeMillis();
		System.out.println("开始执行时间：" + startTime / 1000);
		worker1.start();
		Thread.sleep(3000);
		worker2.start();
		
		// 主线程会等两条线程执行完毕后 再继续执行
		countDownLatch.await();
		long endTime = System.currentTimeMillis();
		System.out.println("执行完毕时间：" + endTime / 1000);
		System.out.println("耗时：" + (endTime - startTime) / 1000 + "秒");
	}
}

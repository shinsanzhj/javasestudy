package com.zhj.study.javase.concurrency.interrupt;

/**
 * 研究线程中断
 * @author zhj
 *
 */
public class ThreadInterruptDemo {

	public static void main(String[] args) throws Exception {
//		NotInterruptTask target = new NotInterruptTask();
//		Thread thread = new Thread(target);
//		thread.start();
//		Thread.sleep(5000);
//		System.out.println("\t打断线程...");
//		thread.interrupt();
		
		InterruptTask target = new InterruptTask();
		Thread thread = new Thread(target);
		thread.start();
		Thread.sleep(3000);
		System.out.println("\t打断线程...");
		thread.interrupt();
	}

	
	private static class NotInterruptTask implements Runnable {
		public void run() {
			while(true) {
				// interrupted()会清除中断状态位
//				System.out.println("线程正在执行...[interrupted():" + Thread.currentThread().interrupted() + "]");
				System.out.println("线程正在执行...[isInterrupted():" + Thread.currentThread().isInterrupted() + "]");
				long currentTime = System.currentTimeMillis();
				
				//不是sleep 1秒，该线程会一直占用CPU
				//但是效果和sleep 1秒差不多
				while(System.currentTimeMillis() - currentTime < 1000) {
					
				}
			}
		}
	}
	
	private static class InterruptTask implements Runnable {
		public void run() {
			while(!Thread.currentThread().interrupted()) {
				try {
					System.out.println("线程准备睡2秒...");
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					System.out.println("线程被中断了...");
//					e.printStackTrace();
					break;
				}
				System.out.println("线程醒来了...准备执行2秒的业务逻辑操作...");
				long currentTime = System.currentTimeMillis();
				while(System.currentTimeMillis() - currentTime < 2000) {
					
				}
			}
			System.out.println("线程执行完毕...");
		}
	}
}

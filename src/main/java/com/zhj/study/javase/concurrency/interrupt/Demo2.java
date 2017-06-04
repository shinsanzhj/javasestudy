package com.zhj.study.javase.concurrency.interrupt;

/**
 * 多线程 interrupt() interrupted() isInterrupted()
 * @author zhj
 *
 */
public class Demo2 {

	public static void main(String[] args) {
		testIsinterrupted();
//		testInterrupted();
	}
	
	private static void testIsinterrupted() {
		try {
			// 设置线程中断位
			Thread.currentThread().interrupt();
			
			// 不会清除线程中断位，所以调用sleep方法会直接抛出异常，而不会sleep
			System.out.println(Thread.currentThread().isInterrupted());
			
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("叫醒了");
		}
		System.out.println("结束...");
	}
	
	private static void testInterrupted() {
		try {
			// 设置线程中断位
			Thread.currentThread().interrupt();
			
			// 会清除线程中断位，调用sleep方法能实现睡眠功能
			System.out.println(Thread.currentThread().interrupted());
			
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("叫醒了");
		}
		System.out.println("结束...");
	}
}

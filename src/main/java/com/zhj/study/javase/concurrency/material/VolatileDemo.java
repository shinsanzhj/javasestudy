package com.zhj.study.javase.concurrency.material;

import java.util.ArrayList;

import org.junit.internal.ArrayComparisonFailure;

public class VolatileDemo {

	private static boolean nomalFlag = true;
	private static volatile boolean volatileFlag = true;
	
	private static class Task1 implements Runnable {
		public void run() {
			long startTime = System.currentTimeMillis();
			while(nomalFlag) {
//				System.out.println("继续执行Task1任务");
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
			System.out.println("耗时：" + ((System.currentTimeMillis() - startTime) / 1000) + "秒");
		}
	}
	
	private static class Task2 implements Runnable {
		public void run() {
			while(true) {
				nomalFlag = false;
//				try {
//					Thread.sleep(1000);
//					System.out.println("\t继续执行Task2任务");
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		char[] str = new char[10];
		System.arraycopy("abcdefghijklmnopqrst".toCharArray(), 0, str, 0, 11);
		System.out.println(new String(str));
		
		
		
		
		
		
		
		
		
		Thread thread1 = new Thread(new Task1());
		Thread thread2 = new Thread(new Task2());
		
		thread1.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread2.start();
//		nomalFlag = false;
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("主线程执行完毕，主线程中引用的nomalFlag：" + nomalFlag);
	}
}

package com.zhj.study.javase.memery;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

import com.zhj.study.javase.classloader.material.Demo;
import com.zhj.study.javase.memery.material.DemoModel;

public class MainTester {

	public static void main(String[] args) {
//		testMethodStack();
//		testTLAB();
//		testReference();
//		testMemaryAllocate();
	}

	// 测试方法栈
	// JVM参数：-Xss1K【并没有报错，可能 jdk6 已经做了一些优化，如果要报错 则执行方法中被注释的两句】
	private static void testMethodStack() {
		new Thread(new Runnable() {
			
			public void run() {
				loop(1);
			}
			
			private void loop(int i) {
//				System.out.println(i);
//				loop(++i);
				if(i != 2000) {
					loop(++i);
				} else {
					System.out.println("i:" + i);
				}
			}
		}).start();
	}
	
	// 测试TLAB空间使用情况
	// -XX:+PrintTLAB -XX:+PrintGC
	private static void testTLAB() {
		new Thread(new Runnable() {
			Object[] arrs = new Object[10];
			public void run() {
				for(int i = 0; i < 10; i++) {
					new DemoModel();
					try {
						Thread.currentThread().sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(i == 5 || i ==8) {
						System.gc();
					}
				}
			}
		}).start();
	}
	
	// 测试Java的各种引用：强引用、软引用、弱引用
	private static void testReference() {
		// 强引用
		DemoModel demoModel = new DemoModel();
		// 软引用
		SoftReference<DemoModel> softReference = new SoftReference<DemoModel>(new DemoModel());
		softReference.get().setId("aaa");
		System.out.println(softReference.get().getId());
		System.out.println(softReference.get() == null);
		// 弱引用
		WeakReference<DemoModel> weakReference = new WeakReference<DemoModel>(new DemoModel());
		weakReference.get().setId("bbb");
		System.out.println(weakReference.get().getId());
		System.out.println(weakReference.get() == null);
		// 手动调用GC
		System.gc();
		
		System.out.println(softReference.isEnqueued());// false，如果空间不够 也会变成true
		System.out.println(weakReference.isEnqueued());// flase
		System.out.println(weakReference.get() == null);// true
	}
	
	// 测试内存分配情况
	// 参数：-Xms20M -Xmn10M -Xmx20M -XX:SurvivorRatio=8 -XX:+UseParallelGC
	// 断点调试
	// 查看命令：jstat -gcutil pid
	private static void testMemaryAllocate() {
		byte[] bytes1 = new byte[1024 * 1024 * 2];
		byte[] bytes2 = new byte[1024 * 1024 * 2];
		byte[] bytes3 = new byte[1024 * 1024 * 2];
		System.out.println("准备在 老年代 区域上分配内存");
		byte[] bytes4 = new byte[1024 * 1024 * 4];
	}
}

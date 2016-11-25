package com.zhj.study.javase.memery;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

import com.zhj.study.javase.memery.material.DemoModel;
import com.zhj.study.javase.memery.material.MBObject;

public class MainTester {

	public static void main(String[] args) {
//		testMethodStack();
//		testTLAB();
//		testReference();
//		testMemaryAllocate();
//		testMinorGC();
		
		// 新生代GC
//		testMinorGC_SerialGC();
//		testMinorGC_ParallelGC();
//		testMinorGC_ParNewGC();
		
		// 老年代GC
//		testFullGC_SerialGC();
		testFullGC_ParallelGC();
	}

	// 测试方法栈
	// JVM参数：-Xss1K【并没有报错，可能jdk6已经做了一些优化，如果要报错 则执行方法中被注释的两句】
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
		System.out.println("软引用：" + softReference.get());
		// 弱引用
		WeakReference<DemoModel> weakReference = new WeakReference<DemoModel>(new DemoModel());
		System.out.println("弱引用：" + weakReference.get());
		// 虚引用
		ReferenceQueue<DemoModel> referenceQueue = new ReferenceQueue<DemoModel>();
		PhantomReference<DemoModel> phantomReference = new PhantomReference<DemoModel>(new DemoModel(), referenceQueue);
		System.out.println("虚引用：" + phantomReference.get());
		System.out.println("虚引用：" + phantomReference.isEnqueued());
		
		// 手动调用GC
		System.gc();
		
		System.out.println("软引用：" + softReference.get());// 对象
		System.out.println("软引用：" + softReference.isEnqueued());// false，如果空间不够 也会变成true
		System.out.println("弱引用：" + weakReference.get());// 
		System.out.println("弱引用：" + weakReference.isEnqueued());// flase
		System.out.println("虚引用：" + phantomReference.get());// true
		System.out.println("虚引用：" + phantomReference.isEnqueued());// true
	}
	
	// 测试内存分配情况
	// 参数：-Xms20M -Xmn10M -Xmx20M -XX:SurvivorRatio=8 -verbose:gc -XX:+PrintGCDetails -XX:+UseParallelGC
	// 参数：-Xms20M -Xmn10M -Xmx20M -XX:InitialSurvivorRatio=8 -verbose:gc -XX:+PrintGCDetails -XX:+UseParallelGC
	// 断点调试
	// 查看命令：jstat -gcutil pid
	private static void testMemaryAllocate() {
		byte[] bytes1 = new byte[1024 * 1024 * 2];
		byte[] bytes2 = new byte[1024 * 1024 * 2];
		byte[] bytes3 = new byte[1024 * 1024 * 2];
		System.out.println("准备在 老年代 区域上分配内存");
		byte[] bytes4 = new byte[1024 * 1024 * 4];
	}
	
	// 测试MinorGC的各种情况
	// 1. Minor GC的触发
	// 2. Minor GC时Survivor空间不足的情况下，对象直接进入旧生代
	// 3. 不同GC的日志
	// 参数[默认：并行回收GC]：-Xms40M -Xmx40M -Xmn16M -XX:SurvivorRatio=6 -verbose:gc -XX:+PrintGCDetails
	private static void testMinorGC() {
		//情况1
//		MBObject obj = new MBObject(1);
//		for(int i = 1; i <= 24; i++) {
//			if(i == 11) {
//				System.out.println("Eden空间即将用完");
//			}
//			if(i == 23) {
//				System.out.println("Eden空间再次即将用完");
//			}
//			new MBObject(1);
//		}
		
		//情况2
//		MBObject obj = new MBObject(3);
//		for(int i = 1; i <= 24; i++) {
//			if(i == 9) {
//				System.out.println("Eden空间即将用完");
//			}
//			if(i == 23) {
//				System.out.println("Eden空间再次即将用完");
//			}
//			new MBObject(1);
//		}
		
		//情况3
		//参数[串行GC]：-XX:+UseSerialGC -Xms40M -Xmx40M -Xmn16M -XX:SurvivorRatio=6 -verbose:gc -XX:+PrintGCDetails
		//参数[并行GC]：-XX:+UseParNewGC -Xms40M -Xmx40M -Xmn16M -XX:SurvivorRatio=6 -verbose:gc -XX:+PrintGCDetails
		MBObject obj = new MBObject(1);
		for(int i = 1; i <= 11; i++) {
			if(i == 9) {
				System.out.println("Eden空间即将用完");
			}
			new MBObject(1);
		}
	}
	
	/*
	 * 测试Minor GC阶段采用串行GC的情况
	 * 参数：-Xms40M -Xmx40M -Xmn15M -XX:SurvivorRatio=3 -verbose:gc -XX:+PrintGCDetails -XX:+UseSerialGC
	 * 可选参数： -XX:MaxTenuringThreshold=2【S0或S1空间中的对象要经历几次Minor GC才进入老年代空间】
	 * 可选参数： -XX:PretenureSizeThreshold=2621440【对象超过2.5M就直接在Old空间分配内存，不能设置太小 不然容易Full GC】
	 * 说明：新生代【Eden：9M S0、S1：3M】	老年代：25M
	 */
	private static void testMinorGC_SerialGC() {
		// 根据对象的声明顺序和大小，JVM会自动优化 在GC时到底哪些obj会放入S区，哪些会放入Old区
		MBObject obj1 = new MBObject(1);
		MBObject obj2 = new MBObject(2);
		MBObject obj3 = new MBObject(3);
		
		// 触发第一次GC：obj1在S1区 obj2、obj3在Old区
		batchCreateObject(1, 8);
		// 触发第二次GC：obj1在S0区 obj2、obj3在Old区
		batchCreateObject(1, 8);
		// 触发第三次GC：obj1在S1区 obj2、obj3在Old区
		batchCreateObject(1, 8);
	}
	
	
	/*
	 * 测试Minor GC阶段采用并行回收GC的情况
	 * 参数：-Xms40M -Xmx40M -Xmn15M -XX:SurvivorRatio=3 -verbose:gc -XX:+PrintGCDetails -XX:+UseParallelGC
	 * 附加参数： -XX:InitialSurivorRatio=[-XX:SurivorRation【比较直观】 + 2]
	 * 附加参数： -XX:-UseAdaptiveSizePolicy【由于该GC会在运行一段时间后动态调整Eden、S0、S1大小，如果需要固定大小，可以通过该参数配置】
	 * 附加参数： -XX:ParallelGCThreads=4【强制指定并行回收GC的线程数目】
	 * 说明：新生代【Eden：9M S0、S1：3M】	老年代：25M
	 */
	private static void testMinorGC_ParallelGC() {
		// 情况：
		MBObject obj1 = new MBObject(1);
		MBObject obj2 = new MBObject(2);
		MBObject obj3 = new MBObject(3);
		
		// 触发第一次GC：obj1在S1区 obj2、obj3在Old区
		// 说明：【分配第二个2M大小的时候触发Minor GC】
		batchCreateObject(2, 2);
		// 不会触发Minor GC
		// 说明：因为PSGC在为对象分配内存时，Eden空间不够的情况下，会判断对象所需的内存是否超过Eden的一半，
		// 		 如果超过，则直接在老年代空间分配该对象的内存，所以这一次不会触发Minor GC
		batchCreateObject(1, 5);
		// 不会触发Minor GC
		batchCreateObject(1, 5);
	}
	
	/*
	 * 测试Minor GC阶段采用并行GC的情况【和并行回收GC的主要区别在于并行GC实现了一些接口，可以配合老年代CMS GC工作】
	 * 参数： -Xms40M -Xmx40M -Xmn15M -XX:SurvivorRatio=3 -verbose:gc -XX:+PrintGCDetails -XX:+UseParNewGC
	 * 说明：新生代【Eden：9M S0、S1：3M】	老年代：25M
	 */
	private static void testMinorGC_ParNewGC() {
		// 情况：
		MBObject obj1 = new MBObject(1);
		MBObject obj2 = new MBObject(2);
		MBObject obj3 = new MBObject(3);
		
		// 触发第一次GC：obj1在S1区 obj2、obj3在Old区
		// 说明：【分配第二个2M大小的时候触发Minor GC】
		batchCreateObject(2, 2);
		
		// 不会触发Minor GC
		// 说明：原因和PSGC一样
		batchCreateObject(1, 5);
	}
	
	/*
	 * 测试Full GC阶段采用串行GC的情况
	 * 参数： -Xms40M -Xmx40M -Xmn15M -XX:SurvivorRatio=3 -verbose:gc -XX:+PrintGCDetails -XX:+UseSerialGC
	 * 可选参数： -XX:PretenureSizeThreshold=5242880【对象超过5M就直接在Old空间分配内存，不能设置太小 不然容易Full GC】
	 * 说明：新生代【Eden：9M S0、S1：3M】	老年代：25M
	 */
	private static void testFullGC_SerialGC() {
		MBObject obj1 = new MBObject(5);
		batchCreateObject(1, 15);
		// 只触发老年代GC
		// [GC [Tenured: 20480K->5283K(25600K), 0.0038341 secs] 21033K->5283K(37888K), [Perm : 3046K->3046K(21248K)], 0.0038713 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
		MBObject obj2 = new MBObject(5);
		MBObject obj3 = new MBObject(7);//这个对象按理来说应该分配在Old区，但是却分配到了Eden区
		// 往老年代添加一个6M的对象【可清理】
		batchCreateObject(1, 7);

		// 触发MinorGC，（本来不会触发）就是因为obj3没有分配在Old区导致
		// [GC [DefNew: 7536K->0K(12288K), 0.0030516 secs] 25108K->24739K(37888K), 0.0030811 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
		MBObject obj4 = new MBObject(4);
		// 会触发MinorGC，然后会obj4请求晋升到老年代，发现老年代空间不够，会触发FullGC【显示的并不是full gc】
		// [GC [DefNew: 8286K->8286K(12288K), 0.0000160 secs][Tenured: 24739K->21667K(25600K), 0.0054403 secs] 33026K->21667K(37888K), [Perm : 3055K->3055K(21248K)], 0.0054961 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
		batchCreateObject(2, 4);
	}
	
	/*
	 * 测试Full GC阶段采用并行回收GC的情况
	 * 参数： -Xms40M -Xmx40M -Xmn15M -XX:SurvivorRatio=3 -verbose:gc -XX:+PrintGCDetails -XX:+UseParallelOldGC
	 * 说明：新生代【Eden：9M S0、S1：3M】	老年代：25M
	 * TODO 疑问：控制台打印的Full GC日志表示的情况和jstat打印的不一样。到底Full GC会触发MinorGC吗？
	 */
	private static void testFullGC_ParallelGC() {
		MBObject obj1 = new MBObject(8);
		MBObject obj2 = new MBObject(5);//直接到Old
		MBObject obj3 = new MBObject(5);//直接到Old
		batchCreateObject(2, 3);//触发MinorGC：obj1到Old；obj1到Old后，由于Old使用空间到达了阈值，所以触发FullGC
		
		batchCreateObject(1, 5);//直接到Old，虽然超过阈值，不过不会再触发FullGC
		batchCreateObject(1, 5);//直接到Old，Old空间不够，触发FullGC
	}
	
	/*
	 * 
	 */
	private static void testFullGC_CMSGC() {
		
	}

	/**
	 * 批量创建对象
	 * @param objectCount 要创建的对象个数
	 * @param objectSize 每个对象的大小（单位：MB）
	 *
	 * <pre>
	 * 修改日期		修改人	修改原因
	 * 2016-11-25	钟华杰	新建
	 * </pre>
	 */
	private static void batchCreateObject(int objectCount, int objectSize) {
		for(int i = 0; i < objectCount; i++) {
			System.out.println("准备创建第" + (i + 1) + "个大小为" + objectSize + "MB的对象");
			new MBObject(objectSize);
			System.out.println("完成创建第" + (i + 1) + "个大小为" + objectSize + "MB的对象");
		}
	}
}

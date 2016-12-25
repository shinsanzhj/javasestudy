package com.zhj.study.javase.concurrency.material;

public class InterruptDemo {

	private static Object lock = new Object();
	private static boolean waitFlag = true;
	private static Thread[] threads = null;
	
	private static class WaitTask implements Runnable {
		public void run() {
			synchronized (lock) {
				while(waitFlag) {
					System.out.println(Thread.currentThread().getName() + "...waitFlag:" + waitFlag);
					try {
						showThreadsState();
						lock.wait();
					} catch (InterruptedException e) {
						System.out.println(Thread.currentThread().getName() + "的wait被中断，并且开始执行了");
					}
				}
				System.out.println(Thread.currentThread().getName() + "[跳出循环]...waitFlag:" + waitFlag);
			}
		}
	}
	
	private static class NotifyTask implements Runnable {
		public void run() {
			synchronized (lock) {
				waitFlag = false;
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "执行完毕...");
				showThreadsState();
			}
		}
	}
	
	
	private static void showThreadsState() {
		StringBuffer sb = new StringBuffer();
		sb.append(Thread.currentThread().getName());
		for(int i = 0; i < threads.length; i++) {
			sb.append(threads[i].getName()).append(":").append(threads[i].getState().name()).append("\t");
		}
		System.out.println(sb.toString());
	}
	
	/**
	 * @param args
	 *
	 * <pre>
	 * 修改日期		修改人	修改原因
	 * 2016-12-23	钟华杰	新建
	 * </pre>
	 */
	public static void main(String[] args) {
		// 线程wait被打断，但是对象锁又不在该线程中，这样结果如何
		Thread thread1 = new Thread(new WaitTask());
		thread1.setName("【等待线程1】");
		Thread thread2 = new Thread(new NotifyTask());
		thread2.setName("【唤醒线程】");
		
		threads = new Thread[2];
		threads[0] = thread1;
		threads[1] = thread2;
		
		// 保证线程1先启动，先进入wait状态
		thread1.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 当线程1进入waiting状态，线程2开启，并且在接下来的10秒内会一直拥有对象锁
		thread2.start();
		
		// 在第5秒左右时，主线程打断线程1，验证线程1是否能立即醒来，还是说要等线程2释放完对象锁之后才能执行
		// 答案：【要等线程2释放完对象锁之后才能执行】
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread1.interrupt();
		System.out.println("主线程中断" + thread1.getName() + ",中断标识：" + thread1.isInterrupted());
	}

}

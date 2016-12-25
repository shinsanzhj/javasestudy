package com.zhj.study.javase.concurrency.material;

public class WaitNotifyNotifyAllDemo {

	private static Object lock = new Object();
	private static boolean waitFlag = true;
	private static Thread[] threads = null;
	
	private static class WaitTask implements Runnable {
		public void run() {
			synchronized (lock) {
				while(waitFlag) {
					System.out.println(Thread.currentThread().getName() + "...waitFlag:" + waitFlag);
					try {
						Thread.sleep(1000);
						showThreadsState();
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(Thread.currentThread().getName() + "[跳出循环]...waitFlag:" + waitFlag);
				showThreadsState();
			}
		}
	}
	
	private static class WaitTimeTask implements Runnable {
		public void run() {
			synchronized (lock) {
				try {
//					lock.wait(5000);
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static class NotifyTask implements Runnable {
		public void run() {
			synchronized (lock) {
				waitFlag = false;
//				lock.notify();
				System.out.println(Thread.currentThread().getName() + "...waitFlag:" + waitFlag);
				showThreadsState();
				lock.notifyAll();
				System.out.println(Thread.currentThread().getName() + "[notifyAll]...waitFlag:" + waitFlag);
				showThreadsState();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			synchronized (lock) {
				System.out.println(Thread.currentThread().getName() + "[第二次获取锁]...waitFlag:" + waitFlag);
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
//		testWaitNotify();
		testWaitTimeThreadState();
	}
	
	private static void testWaitNotify() {
		Thread thread1 = new Thread(new WaitTask());
		thread1.setName("【等待线程1】");
		Thread thread3 = new Thread(new WaitTask());
		thread3.setName("【等待线程2】");
		Thread thread2 = new Thread(new NotifyTask());
		thread2.setName("【唤醒线程】");
		
		threads = new Thread[2];
		threads[0] = thread1;
		threads[1] = thread3;
		threads[1] = thread2;
		
		thread1.start();
		thread2.start();
		thread3.start();
	}
	
	private static void testWaitTimeThreadState() {
		Thread thread1 = new Thread(new WaitTimeTask());
		thread1.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("线程状态：" + thread1.getState().name());
	}

}

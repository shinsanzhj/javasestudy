package com.zhj.study.javase.collection.modify;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Demo {

	public static void main(String[] args) {
//		List<String> list = new ArrayList<String>();
		List<String> list = Collections.synchronizedList(new ArrayList<String>());
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("remove");
		list.add("5");
		list.add("6");
		list.add("remove");
		
		TaskFor taskFor = new TaskFor(list);
		TaskIterator taskIterator = new TaskIterator(list);
		
		Thread t1 = new Thread(taskFor);
		Thread t2 = new Thread(taskIterator);
		
		t1.start();
		t2.start();
	}

	private static class TaskFor implements Runnable {
		
		private List<String> list;
		
		public TaskFor(List<String> list) {
			this.list = list;
		}
		
		@Override
		public void run() {
			for(String str : list) {
				System.out.println("str:" + str);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("list大小：" + list.size());
		}
	}
	
	private static class TaskIterator implements Runnable {
		
		private List<String> list;
		
		public TaskIterator(List<String> list) {
			this.list = list;
		}
		
		@Override
		public void run() {
			Iterator<String> it = list.iterator();
			while(it.hasNext()) {
				String str = it.next();
				if(str.equals("remove")) {
					it.remove();
				}
				try {
					Thread.sleep(800);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("经过移除元素后，list大小：" + list.size());
		}
	}
}

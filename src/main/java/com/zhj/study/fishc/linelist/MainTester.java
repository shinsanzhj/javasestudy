package com.zhj.study.fishc.linelist;

import com.zhj.study.fishc.linelist.linklist.HeadLinkList;
import com.zhj.study.fishc.linelist.linklist.TailLinkList;

public class MainTester {

	public static void main(String[] args) {
		String[] params = new String[] {"a", "b", "c", "d", "e"};
//		testHeadLinkList(params);
		testTailLinkList(params);
	}

	// 测试头插法单链表
	private static void testHeadLinkList(Object[] params) {
		HeadLinkList list = new HeadLinkList(params);
		list.printList();
	}
	
	// 测试头尾插法单链表
	private static void testTailLinkList(Object[] params) {
		TailLinkList list = new TailLinkList(params);
		list.printList();
	}
	
	
}

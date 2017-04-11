package com.zhj.study.fishc.linelist.linklist;

/**
 * 头插法创建单链表
 * @author zhj
 *
 */
public class HeadLinkList {

	private Node header;
	
	public HeadLinkList() {
		
	}
	
	public HeadLinkList(Object[] datas) {
		init(datas);
	}
	
	private void init(Object[] datas) {
		if(null == datas || datas.length < 1) {
			throw new RuntimeException("参数错误");
		}
		
		for(int i = 0; i < datas.length; i++) {
			Node node = new Node(datas[i]);
			if(null != header) {
				node.setNext(header);
				header = node;
			} else {
				header = node;
			}
		}
	}
	
	public void printList() {
		Node current = header;
		while(current != null) {
			System.out.println(current.getData());
			current = current.getNext();
		}
	}
	
	// 节点元素
	private static class Node {
		private Object data;
		private Node next;
		
		public Node() {
			
		}
		
		public Node(Object data) {
			this.data = data;
		}
		
		public Object getData() {
			return data;
		}
		public void setData(Object data) {
			this.data = data;
		}
		public Node getNext() {
			return next;
		}
		public void setNext(Node next) {
			this.next = next;
		}
	}
}

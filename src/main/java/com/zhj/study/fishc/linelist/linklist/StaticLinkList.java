package com.zhj.study.fishc.linelist.linklist;

/**
 * 静态链表
 * @author zhj
 *
 */
public class StaticLinkList {

	private int maxSize;
	private Node[] nodeArr;
	
	
	public StaticLinkList() {
		init();
	}
	
	private void init() {
		nodeArr = new Node[maxSize];
		
		Node header = new Node();
		nodeArr[0] = header;
		header.setCusor(1);
		
		Node tail = new Node();
		nodeArr[maxSize - 1] = tail;
		tail.setCusor(1);
	}
	
	public void insert(Object data) {
		Node node = new Node();
		node.setData(data);
		
		int insertPoint = nodeArr[0].getCusor();
//		node
//		nodeArr[insertPoint] = node;
		
	}
	
	
	
	
	
	
	
	
	private static class Node {
		private Object data;
		private int cusor;
		public Object getData() {
			return data;
		}
		public void setData(Object data) {
			this.data = data;
		}
		public int getCusor() {
			return cusor;
		}
		public void setCusor(int cusor) {
			this.cusor = cusor;
		}
	}
}

package com.zhj.study.javase.collection.material;

import java.util.HashSet;
import java.util.Set;

public class Button {

	private String id;
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
		Button target = (Button) obj;
		return target.getId().equals(this.id);
	}
	
	@Override
	public int hashCode() {
		return (this.id + this.name).hashCode();
	}
	
	public static void main(String[] args) {
		Button button1 = new Button();
		button1.setId("btn1");
		button1.setName("按钮1号");
		System.out.println(button1.hashCode());//-1440683456
		
		Button button2 = new Button();
		button2.setId("btn1");
		button2.setName("按钮2号");
		System.out.println(button2.hashCode());//-1440683425
		
		System.out.println(button1.equals(button2));//true
		
		
		Set<Button> btns = new HashSet<Button>();
		btns.add(button1);
		btns.add(button2);
		
		System.out.println(btns.size());//2
	}
}

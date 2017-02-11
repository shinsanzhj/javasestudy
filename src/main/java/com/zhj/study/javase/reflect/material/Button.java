package com.zhj.study.javase.reflect.material;

public class Button {

	private int id;
	
	public Button() {
		
	}
	
	public Button(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "[Button_" + this.id + "]" + super.toString();
	}
}

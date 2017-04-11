package com.zhj.study.javase.rpc.personal.lib.dto;

import java.io.Serializable;

public class World implements Serializable {
	private static final long serialVersionUID = -3837954761444881006L;
	
	private Integer id;
	private String name;
	
	public World() {
		
	}
	
	public World(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "[世界ID：" + this.id + ",名称：" + this.name + "]";
	}
}

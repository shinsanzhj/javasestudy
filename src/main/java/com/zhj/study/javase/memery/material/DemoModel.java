package com.zhj.study.javase.memery.material;

public class DemoModel {

	private String id;
	private String name;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	protected void finalize() throws Throwable {
		System.out.println("清理对象： " + this);
		super.finalize();
	}
}

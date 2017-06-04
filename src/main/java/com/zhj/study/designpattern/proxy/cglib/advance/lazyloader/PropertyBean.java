package com.zhj.study.designpattern.proxy.cglib.advance.lazyloader;

public class PropertyBean {

	private String key;
	private String Object;
	
	
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the object
	 */
	public String getObject() {
		return Object;
	}
	/**
	 * @param object the object to set
	 */
	public void setObject(String object) {
		Object = object;
	}
}

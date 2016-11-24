package com.zhj.study.javase.memery.material;

/**
 * 构造占用[size]MB大小内存的对象
 * 
 *
 * <pre>
 * 修改日期		修改人	修改原因
 * 2016-11-22	钟华杰	新建
 * </pre>
 */
public class MBObject {
	private byte[] memory;
	
	public MBObject() {
		
	}
	
	public MBObject(int size) {
		memory = new byte[size * 1024 * 1024];
	}
}

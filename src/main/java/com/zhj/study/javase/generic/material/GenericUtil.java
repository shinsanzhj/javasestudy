package com.zhj.study.javase.generic.material;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型工具类
 * @author zhj
 *
 */
public class GenericUtil<K> {

	// 根据类返回对象
	public static <T extends BaseDTO> T getObject(Class<T> c) {
		try {
			return c.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static <T extends BaseDTO> T initDTO(T obj) {
		BaseDTO dto = (BaseDTO) obj;
		dto.setId("111");
		dto.setName("222");
		return obj;
	}
	
	// 类上定义的K声明，只对实例方法有效，静态方法还是要自己再声明一次T
	public K getDTO() {
		return null;
	}
}

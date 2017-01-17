package com.zhj.study.javase.generic.material;

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
}

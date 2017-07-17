package com.zhj.study.javase.collection.modify;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 集合类有实现对应的迭代器,通过迭代器删除,能实现对应的删除动作
 * 比如:
 * 	map的keys迭代器,通过删除keys中的key,能实现删除map中对应的key-value
 *  map的values迭代器,通过删除values中的value,能实现删除map中对应的key-value
 * 
 *
 * <pre>
 * 修改日期		修改人	修改原因
 * 2017-6-20	钟华杰	新建
 * </pre>
 */
public class MapDemo {

	/**
	 * @param args
	 *
	 * <pre>
	 * 修改日期		修改人	修改原因
	 * 2017-6-20	钟华杰	新建
	 * </pre>
	 */
	public static void main(String[] args) {
//		test1();
		test2();
	}

	private static void test1() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("key3", "value3");
		map.put("key4", "value4");
		map.put("key5", "value5");
		printMap(map);
		
		Iterator<String> it = map.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			if("key2".equals(key) || "key4".equals(key)) {
//				map.remove(key); ConcurrentModificationException
				it.remove();
			}
		}
		printMap(map);
	}
	
	private static void test2() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("key3", "value3");
		map.put("key4", "value4");
		map.put("key5", "value5");
		printMap(map);
		
		Iterator<String> it = map.values().iterator();
		while(it.hasNext()) {
			String value = it.next();
			if("value2".equals(value) || "value4".equals(value)) {
//				map.remove(key); ConcurrentModificationException
				it.remove();
			}
		}
		printMap(map);
	}
	
	private static void printMap(Map<String, String> map) {
		Iterator<String> it = map.keySet().iterator();
		System.out.println("\nmap内容如下...");
		while(it.hasNext()) {
			String key = it.next();
			String value = map.get(key);
			System.out.println(key + ":" + value);
		}
	}
}

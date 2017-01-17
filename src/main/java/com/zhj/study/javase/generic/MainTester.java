package com.zhj.study.javase.generic;

import java.util.ArrayList;
import java.util.List;

import com.zhj.study.javase.generic.material.BaseDTO;
import com.zhj.study.javase.generic.material.CatDTO;
import com.zhj.study.javase.generic.material.GenericUtil;

/**
 * 泛型相关
 * @author zhj
 *
 */
public class MainTester {

	public static void main(String[] args) {
//		CatDTO cat = GenericUtil.getObject(CatDTO.class);
//		cat.setCatName("aaa");
//		System.out.println(cat.getCatName());
		
//		CatDTO dtoo = GenericUtil.initDTO(new CatDTO());
//		System.out.println(dtoo.getName());
//		System.out.println(dtoo.getId());
		
//		List list = new ArrayList();
//		list.add("zhj");
//		list.add(123);
//		
//		System.out.println(list.get(0));
//		System.out.println(list.get(1));
		
		List<?> list2 = null;
		List<String> list3 = new ArrayList<String>();
		list3.add("1");
		list3.add("2");
		
		list2 = list3;
		System.out.println(list2.get(0));
		System.out.println(list2.get(1));
		
	}
	
}

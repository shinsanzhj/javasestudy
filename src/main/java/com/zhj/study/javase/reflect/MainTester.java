package com.zhj.study.javase.reflect;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.zhj.study.javase.reflect.material.Button;
import com.zhj.study.javase.reflect.material.ToolBar;

public class MainTester {

	public static void main(String[] args) {
//		testGetComponentType();
		testArray_newInstance();
	}

	// getComponentType返回原生数组中的对象的类型名称，前面不带[前缀
	private static void testGetComponentType() {
		Button b = new Button();
		System.out.println(b.getClass().getName());//com.zhj.study.javase.reflect.material.Button
		System.out.println(b.getClass().getComponentType());//null
		
		
		List<Button> l1 = new ArrayList<Button>();
		l1.add(new Button());
		l1.add(new Button());
		System.out.println(l1.getClass().getName());//java.util.ArrayList
		System.out.println(l1.getClass().getComponentType());//null
		
		
		Button[] arr1 = new Button[3];
		arr1[0] = new Button();
		arr1[1] = new Button();
		arr1[2] = new Button();
		System.out.println(arr1.getClass().getName());//[Lcom.zhj.study.javase.reflect.material.Button;
		System.out.println(arr1.getClass().getComponentType().getName());//com.zhj.study.javase.reflect.material.Button
		
		
		int[] arr2 = new int[3];
		arr2[0] = 1;
		arr2[1] = 1;
		arr2[2] = 1;
		System.out.println(arr2.getClass().getName());//[I
		System.out.println(arr2.getClass().getComponentType().getName());//int
		
		Integer[] arr21 = new Integer[3];
		arr21[0] = 1;
		arr21[1] = 1;
		arr21[2] = 1;
		System.out.println(arr21.getClass().getName());//[Ljava.lang.Integer;
		System.out.println(arr21.getClass().getComponentType().getName());//java.lang.Integer
		
		double[] arr22 = new double[3];
		arr22[0] = 1;
		arr22[1] = 1;
		arr22[2] = 1;
		System.out.println(arr22.getClass().getName());//[D
		System.out.println(arr22.getClass().getComponentType().getName());//double
		
		Object[] arr3 = new Object[3];
		arr3[0] = new Button();
		arr3[1] = new MainTester();
		arr3[2] = new ToolBar();
		System.out.println(arr3.getClass().getName());//[Ljava.lang.Object;
		System.out.println(arr3.getClass().getComponentType().getName());//java.lang.Object
	}
	
	// 反射生成一个数组，返回的是一个原生数组，不是一个集合类
	private static void testArray_newInstance() {
		System.out.println(Array.newInstance(Button.class, 3).getClass().getName());
		
		Button[] btns = (Button[]) Array.newInstance(Button.class, 3);
		System.out.println(btns.length);//3
		
		// 转换成集合类
		List<Button> list = Arrays.asList(btns);
		System.out.println(list.size());//3
		System.out.println(list.getClass().getName());//java.util.Arrays$ArrayList
	}
}

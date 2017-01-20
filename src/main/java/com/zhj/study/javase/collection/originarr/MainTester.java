package com.zhj.study.javase.collection.originarr;

import java.util.Arrays;

import com.zhj.study.javase.reflect.material.Button;

public class MainTester {

	/**
	 * @param args
	 *
	 * <pre>
	 * 修改日期		修改人	修改原因
	 * 2017-1-19	钟华杰	新建
	 * </pre>
	 */
	public static void main(String[] args) {
//		test_System_arraycopy();
		test_Arrays_copyof();
	}

	
	private static void test_System_arraycopy() {
		Button[] btns = new Button[3];
		btns[0] = new Button(1);
		btns[1] = new Button(2);
		btns[2] = new Button(3);
		
		Button[] btns2 = new Button[2];
//		System.arraycopy(btns, 0, btns2, 0, 3);超出数组边界，报异常
		System.arraycopy(btns, 0, btns2, 0, 2);
		System.out.println(btns2[0]);
	}
	
	private static void test_Arrays_copyof() {
		Button[] btns = new Button[3];
		btns[0] = new Button(1);
		btns[1] = new Button(2);
		btns[2] = new Button(3);
		Button[] newArr = Arrays.copyOf(btns, 0);
		
		System.out.println(btns);
		System.out.println(newArr);
		System.out.println(btns.length);
		System.out.println(newArr.length);
//		System.out.println(btns[0]);
//		System.out.println(newArr[0]);
	}
}

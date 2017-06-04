package com.zhj.study.javase.memery.ref;

import java.lang.ref.SoftReference;

import com.zhj.study.javase.memery.material.MBObject;

/**
 * 软引用DEMO
 * @author zhj
 *
 */
public class SoftRefDemo {

	public static void main(String[] args) {
		testSoftRef();
	}

	private static void testSoftRef() {
		MBObject mbObject = new MBObject();
		
		SoftReference<MBObject> reference = new SoftReference<MBObject>(mbObject);
		
		System.out.println(mbObject);
		System.out.println(reference.get());
		
		System.gc();
		
		System.out.println(mbObject);
		System.out.println(reference.get());
	}
	
}

package com.zhj.study.javase.memery.ref;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

import org.junit.Assert;

import com.zhj.study.javase.memery.material.MBObject;

/**
 * 弱引用DEMO
 * @author zhj
 *
 */
public class WeakRefDemo {

	public static void main(String[] args) {
//		testWeakRef();
		testWeakHashMap();
	}

	private static void testWeakRef() {
		MBObject mbObject = new MBObject();
		
		WeakReference<MBObject> weakReference = new WeakReference<MBObject>(mbObject);
		
		Assert.assertSame(mbObject, weakReference.get());
		
		mbObject = null;
		
		System.gc();
		
		Assert.assertNull(weakReference.get());
	}
	
	private static void testWeakHashMap() {
		String key = "myKey";
		MBObject mbObject = new MBObject();
		
		WeakHashMap<String, MBObject> map = new WeakHashMap<String, MBObject>();
		
		map.put(key, mbObject);
		
		System.out.println(map.containsKey(key));
		System.out.println(map.containsValue(mbObject));
		
		mbObject = null;
		
		System.gc();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(map.containsKey(key));
		System.out.println(map.containsValue(mbObject));
	}
}

package com.zhj.study.javase.memery.ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

import com.zhj.study.javase.memery.material.NotFinalizableDemoModel;

/**
 * 虚引用
 * @author zhj
 *
 */
public class PhantomRefDemo {

	// -verbose:gc
	public static void main(String[] args) throws InterruptedException {
		// 虚引用
		ReferenceQueue<NotFinalizableDemoModel> referenceQueue = new ReferenceQueue<NotFinalizableDemoModel>();
		PhantomReference<NotFinalizableDemoModel> phantomReference = new PhantomReference<NotFinalizableDemoModel>(new NotFinalizableDemoModel("001", "zhj"), referenceQueue);
		System.out.println("虚引用：" + phantomReference.get());
		System.out.println("虚引用：" + phantomReference.isEnqueued());
		
		System.gc();
		Thread.sleep(5000);
		
		System.out.println("虚引用：" + phantomReference.get());
		System.out.println("虚引用：" + phantomReference.isEnqueued());
	}

}

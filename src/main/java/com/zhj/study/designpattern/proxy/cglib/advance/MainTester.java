package com.zhj.study.designpattern.proxy.cglib.advance;

import com.zhj.study.designpattern.proxy.HaierWasher;
import com.zhj.study.designpattern.proxy.Washer;
import com.zhj.study.designpattern.proxy.cglib.CglibWasherMethodInterceptor;
import com.zhj.study.designpattern.proxy.cglib.advance.callbackfilter.TargetMethodCallbackFilter;
import com.zhj.study.designpattern.proxy.cglib.advance.callbackfilter.TargetResultFixedValue;
import com.zhj.study.designpattern.proxy.cglib.advance.lazyloader.LazyBean;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

public class MainTester {

	/**
	 * @param args
	 *
	 * <pre>
	 * 修改日期		修改人	修改原因
	 * 2017-3-22	钟华杰	新建
	 * </pre>
	 */
	public static void main(String[] args) {
//		testCglibCallbackFilter();
		testCglibLazyBean();
	}

	private static void testCglibCallbackFilter() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(HaierWasher.class);
		
		CallbackFilter callbackFilter = new TargetMethodCallbackFilter();
		
		Callback noOperate = NoOp.INSTANCE;
		Callback advice = new CglibWasherMethodInterceptor();
		Callback fixedResult = new TargetResultFixedValue();
		Callback[] callbacks = new Callback[] {noOperate, advice, fixedResult};
		
		enhancer.setCallbacks(callbacks);
		enhancer.setCallbackFilter(callbackFilter);
		Washer washer = (Washer) enhancer.create();
		washer.autoWash();
	}
	
	private static void testCglibLazyBean() {
		LazyBean lazyBean = new LazyBean("1", "zhj");
		System.out.println(lazyBean.getId());
		System.out.println(lazyBean.getName());
		System.out.println(lazyBean.getLazyLoaderBean().getKey());
		System.out.println(lazyBean.getLazyLoaderBean().getObject());
		System.out.println(lazyBean.getLazyDispatcherBean().getKey());
		System.out.println(lazyBean.getLazyDispatcherBean().getObject());
	}
}

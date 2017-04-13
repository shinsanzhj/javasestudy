package com.zhj.study.designpattern.proxy.cglib.advance.callbackfilter;

import net.sf.cglib.proxy.FixedValue;

public class TargetResultFixedValue implements FixedValue {

	@Override
	public Object loadObject() throws Exception {
		System.out.println("锁定结果");
		return 999;
	}

}

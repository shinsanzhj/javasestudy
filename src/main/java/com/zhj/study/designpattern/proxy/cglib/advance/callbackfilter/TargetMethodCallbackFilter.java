package com.zhj.study.designpattern.proxy.cglib.advance.callbackfilter;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.CallbackFilter;

public class TargetMethodCallbackFilter implements CallbackFilter {

	@Override
	public int accept(Method method) {
		if("autoWash".equals(method.getName())) {
			return 0;
		} else if("setWashTime".equals(method.getName())) {
			return 1;
		} else if("setWashStyle".equals(method.getName())) {
			return 0;
		} else if("start".equals(method.getName())) {
			return 2;
		} else if("stop".equals(method.getName())) {
			return 1;
		}
		return 0;
	}

}

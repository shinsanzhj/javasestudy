package com.zhj.study.designpattern.proxy.normal;

import com.zhj.study.designpattern.proxy.HaierWasher;
import com.zhj.study.designpattern.proxy.Washer;

public class NormalWasherProxy implements Washer {

	private Washer washer;
	
	public NormalWasherProxy() {
		washer = new HaierWasher();
	}
	
	@Override
	public void autoWash() {
		System.out.println("普通静态代理:autoWash()");
		washer.autoWash();
	}

	@Override
	public void setWashTime(int minutes) {
		System.out.println("普通静态代理:setWashTime()");
		washer.setWashTime(minutes);
	}

	@Override
	public void setWashStyle(String style) {
		System.out.println("普通静态代理:setWashStyle()");
		washer.setWashStyle(style);
	}

	@Override
	public void start() {
		System.out.println("普通静态代理:start()");
		washer.start();
	}

	@Override
	public void stop() {
		System.out.println("普通静态代理:stop()");
		washer.stop();
	}

}

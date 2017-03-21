package com.zhj.study.designpattern.proxy;

public class HaierWasher implements Washer {

	@Override
	public void autoWash() {
		System.out.println("海尔洗衣机:一键洗衣");
		setWashTime(50);
		setWashStyle(WASH_STYLE_STANDAR);
		start();
	}

	@Override
	public void setWashTime(int minutes) {
		System.out.println("海尔洗衣机:设置洗涤时间[" + minutes + "分钟]");
	}

	@Override
	public void setWashStyle(String style) {
		System.out.println("海尔洗衣机：设置洗衣风格[" + style + "]");
	}

	@Override
	public void start() {
		System.out.println("海尔洗衣机：开始洗衣");
	}

	@Override
	public void stop() {
		System.out.println("海尔洗衣机：暂停洗衣");
	}

}

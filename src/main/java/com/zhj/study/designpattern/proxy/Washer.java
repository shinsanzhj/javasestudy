package com.zhj.study.designpattern.proxy;

/**
 * 洗衣机接口
 * 
 *
 * <pre>
 * 修改日期		修改人	修改原因
 * 2017-3-21	钟华杰	新建
 * </pre>
 */
public interface Washer {

	public final static String WASH_STYLE_FAST = "快速洗";
	public final static String WASH_STYLE_STANDAR = "标准洗";
	public final static String WASH_STYLE_STRONG = "强力洗";
	
	public void autoWash();
	public void setWashTime(int minutes);
	public void setWashStyle(String style);
	public void start();
	public void stop();
}

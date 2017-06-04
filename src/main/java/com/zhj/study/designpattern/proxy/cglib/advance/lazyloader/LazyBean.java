package com.zhj.study.designpattern.proxy.cglib.advance.lazyloader;

import net.sf.cglib.proxy.Enhancer;

public class LazyBean {

	private String id;
	private String name;
	private PropertyBean lazyLoaderBean;
	private PropertyBean lazyDispatcherBean;
	
	public LazyBean() {
		// TODO Auto-generated constructor stub
	}
	
	public LazyBean(String id, String name) {
		this.id = id;
		this.name = name;
		this.lazyLoaderBean = createLazyLoaderBean();
		this.lazyDispatcherBean = createLazyDispatcherBean();
	}
	
	private PropertyBean createLazyLoaderBean() {
		return (PropertyBean) Enhancer.create(PropertyBean.class, new ConcreteLazyLoader());
	}
	
	private PropertyBean createLazyDispatcherBean() {
		return (PropertyBean) Enhancer.create(PropertyBean.class, new ConcreteLazyDispatcher());
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the lazyLoaderBean
	 */
	public PropertyBean getLazyLoaderBean() {
		return lazyLoaderBean;
	}
	/**
	 * @param lazyLoaderBean the lazyLoaderBean to set
	 */
	public void setLazyLoaderBean(PropertyBean lazyLoaderBean) {
		this.lazyLoaderBean = lazyLoaderBean;
	}
	/**
	 * @return the lazyDispatcherBean
	 */
	public PropertyBean getLazyDispatcherBean() {
		return lazyDispatcherBean;
	}
	/**
	 * @param lazyDispatcherBean the lazyDispatcherBean to set
	 */
	public void setLazyDispatcherBean(PropertyBean lazyDispatcherBean) {
		this.lazyDispatcherBean = lazyDispatcherBean;
	}
}

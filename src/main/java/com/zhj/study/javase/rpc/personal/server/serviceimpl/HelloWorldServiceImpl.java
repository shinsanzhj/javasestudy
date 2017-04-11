package com.zhj.study.javase.rpc.personal.server.serviceimpl;

import com.zhj.study.javase.rpc.personal.lib.dto.World;
import com.zhj.study.javase.rpc.personal.lib.intf.HelloWorldService;


public class HelloWorldServiceImpl implements HelloWorldService {

	@Override
	public World getWorldByName(Integer id, String name) {
		System.out.println("服务端创建一个World对象");
		return new World(id, name);
	}

}

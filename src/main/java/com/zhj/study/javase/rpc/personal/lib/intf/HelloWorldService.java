package com.zhj.study.javase.rpc.personal.lib.intf;

import com.zhj.study.javase.rpc.personal.lib.dto.World;

public interface HelloWorldService {

	public World getWorldByName(Integer id, String name);
}

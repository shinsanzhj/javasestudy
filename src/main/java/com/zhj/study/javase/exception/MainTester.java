package com.zhj.study.javase.exception;

import com.zhj.study.javase.exception.material.RPCException;

public class MainTester {

	public static void main(String[] args) {
		try {
			Invoker.say("rpc");
		} catch(RPCException e) {
			System.out.println("捕获到了RPC异常");
		} catch(Exception e) {
			System.out.println("捕获到了系统异常");
		}
	}
	
	public static class Invoker {
		public static void say(String word) throws Exception {
			System.out.println("调用say方法");
			if("rpc".equals(word)) {
				throw new RPCException();
			}
			if("sys".equals(word)) {
				throw new Exception();
			}
		}
	}
}

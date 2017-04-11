package com.zhj.study.javase.rpc.personal.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

import com.zhj.study.javase.rpc.personal.lib.dto.World;
import com.zhj.study.javase.rpc.personal.lib.intf.HelloWorldService;



public class ClientStub {

	public static <T> T getStub(final Class<T> clazz) {
		
		return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				
				String service = clazz.getName();

				Socket socket = new Socket("127.0.0.1", 8888);
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				
				oos.writeUTF(service);
				oos.writeUTF(method.getName());
				oos.writeObject(method.getParameterTypes());
				oos.writeObject(args);
				
				Object obj = ois.readObject();
				socket.close();
				
				return obj;
			}
		});
	}
	
	public static void main(String[] args) {
		HelloWorldService helloWorldService = ClientStub.getStub(HelloWorldService.class);
		World world = helloWorldService.getWorldByName(1, "哈哈哈别别别1111");
		System.out.println("返回的对象：" + world);
	}
}

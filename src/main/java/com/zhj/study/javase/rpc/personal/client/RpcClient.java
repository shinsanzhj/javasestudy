package com.zhj.study.javase.rpc.personal.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.zhj.study.javase.rpc.personal.lib.dto.World;
import com.zhj.study.javase.rpc.personal.lib.intf.HelloWorldService;

public class RpcClient {

	public static void main(String[] args) throws Exception {
		String service = HelloWorldService.class.getName();
		String method = "getWorldByName";
		Class[] paramTypes = new Class[]{Integer.class, String.class};
		Object[] params = new Object[]{7, "第七"};

		
		Socket socket = new Socket("127.0.0.1", 8888);
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		
		oos.writeUTF(service);
		oos.writeUTF(method);
		oos.writeObject(paramTypes);
		oos.writeObject(params);
		
		Object obj = ois.readObject();
		World world = (World) obj;
		System.out.println("取回的对象：" + world);
		
		socket.close();
	}
}

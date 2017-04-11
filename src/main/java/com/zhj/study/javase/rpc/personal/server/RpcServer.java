package com.zhj.study.javase.rpc.personal.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

import com.zhj.study.javase.rpc.personal.server.serviceimpl.HelloWorldServiceImpl;

public class RpcServer {

	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(8888);
		System.out.println("服务器启动完毕...");
		
		while(true) {
			Thread.sleep(2000);
			Socket client = serverSocket.accept();
			System.out.println(client);
			Task task = new Task();
			task.setClient(client);
			(new Thread(task)).start();
		}
	}
	
	private static class Task implements Runnable {
		private Socket client;
		
		public void setClient(Socket client) {
			this.client = client;
		}
		
		@Override
		public void run() {
			ObjectInputStream ois = null;
			ObjectOutput oos = null;
			HelloWorldServiceImpl serviceImpl = new HelloWorldServiceImpl();
			try {
				System.out.println("服务调用开始");
				ois = new ObjectInputStream(client.getInputStream());
				oos = new ObjectOutputStream(client.getOutputStream());
				
				String serviceStr = ois.readUTF();
				String methodStr = ois.readUTF();
				Class[] paramTypes = (Class[])ois.readObject();
				Object[] params = (Object[])ois.readObject();
				
				Class clazz = Class.forName(serviceStr);
				Method method = clazz.getMethod(methodStr, paramTypes);
				Object result = method.invoke(serviceImpl, params);
				oos.writeObject(result);
				System.out.println("服务调用完毕");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} finally {
				try {
					client.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}

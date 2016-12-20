package com.zhj.study.javase.io.nio.web;

import java.io.ByteArrayOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NIOFileClient {

	private int SIZE = 2;
	private static int BUFFER_SIZE = 128;
	private static InetSocketAddress SERVER_ADDRESS = new InetSocketAddress("127.0.0.1", 8088);
	private static CharsetEncoder charsetEncoder = Charset.forName("GBK").newEncoder();
	
	public static class Download implements Runnable {
		
		public void run() {
			System.out.println("线程【" + Thread.currentThread().getName() + "】开始......");
			long startTime = System.currentTimeMillis();
			try {
				SocketChannel client = SocketChannel.open();
				client.configureBlocking(false);
				Selector selector = Selector.open();
				client.register(selector, SelectionKey.OP_CONNECT);
				
				client.connect(SERVER_ADDRESS);
				
				ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				int total = 0;
				boolean loopFlag = true;
				while(loopFlag) {
					selector.select();
					Iterator<SelectionKey> it = selector.selectedKeys().iterator();
					while(it.hasNext()) {
						SelectionKey key = it.next();
						it.remove();
						if(key.isConnectable()) {
							SocketChannel channel = (SocketChannel) key.channel();
							
							if(channel.isConnectionPending()) {
								System.out.println("连接成功...");
								channel.finishConnect();
								ByteBuffer request = ByteBuffer.wrap(("我是客户端线程【" + Thread.currentThread().getName() + "】").getBytes(charsetEncoder.charset()));
								channel.write(request);
								channel.register(selector, SelectionKey.OP_READ);
							}
							
						} else if(key.isReadable()) {
							SocketChannel channel = (SocketChannel) key.channel();
							buffer.clear();
							int count = channel.read(buffer);
							if(count > 0) {
								System.out.println("线程【" + Thread.currentThread().getName() + "】对应的连接收到" + count + "个字节");
								buffer.flip();
								byte[] tmp = new byte[buffer.limit()];
								buffer.get(tmp);
								bos.write(tmp);
							} else {
//								System.out.println("线程【" + Thread.currentThread().getName() + "】最终结果：" + new String(bos.toByteArray(), charsetEncoder.charset()));
								channel.write(ByteBuffer.wrap(("我是客户端线程【" + Thread.currentThread().getName() + "】").getBytes(charsetEncoder.charset())));
								key.cancel();
								bos.close();
								channel.close();
								loopFlag = false;
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			long endTime = System.currentTimeMillis();
			System.out.println("线程【" + Thread.currentThread().getName() + "】结束......【用时：" + ((endTime - startTime) / 1000) + "秒】");
		}
	}
	
	
	public static void main(String[] args) {
		int threadCount = 5;
		Download target = new Download();
		List<Thread> threads = new ArrayList<Thread>();
		for(int i = 0; i < threadCount; i++) {
			Thread threadTmp = new Thread(target);
			threadTmp.setName((i + 1) + "号");
			threads.add(threadTmp);
		}
		
		long startTime = System.currentTimeMillis();
		for(Thread thread : threads) {
			thread.start();
		}
		//需要用到多线程合并，否则endTime和startTime的值相等
		long endTime = System.currentTimeMillis();
	}
	
}

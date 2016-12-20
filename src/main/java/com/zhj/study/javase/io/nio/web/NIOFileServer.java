package com.zhj.study.javase.io.nio.web;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;

/**
 * 文件服务器【基于非阻塞NIO】
 * @author zhj
 *
 */
public class NIOFileServer {
	// 每次响应的大小
	private static int BLOCK = 256;
	
	// 对客户端请求进行响应
	public class HandleClient {
		private FileChannel fileChannel;
		private ByteBuffer byteBuffer;
		
		public HandleClient() {
			try {
				fileChannel = new FileInputStream("D:/test.log").getChannel();
				byteBuffer = ByteBuffer.allocate(BLOCK);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		public ByteBuffer readBlock() {
			byteBuffer.clear();
			int count = 0;
			try {
				count = fileChannel.read(byteBuffer);
			} catch (IOException e) {
				e.printStackTrace();
			}
			byteBuffer.flip();
			if(count <= 0) {
				return null;
			}
			return byteBuffer;
		}
		
		public void close() {
			try {
				fileChannel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	private Selector selector;
	private String fileName = "";
	private ByteBuffer clientByteBuffer = ByteBuffer.allocate(BLOCK);
	private CharsetDecoder decoder;
	
	public NIOFileServer(int port) {
		try {
			//初始化
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.socket().bind(new InetSocketAddress(port));
			serverSocketChannel.configureBlocking(false);
			selector = Selector.open();
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			
			Charset charset = Charset.forName("GBK");
			decoder = charset.newDecoder();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 启动服务器
	public void listen() {
		int eventCount = 0;
		while(true) {
			try {
				while(true) {
					selector.select();
					Iterator<SelectionKey> it = selector.selectedKeys().iterator();
					while(it.hasNext()) {
						SelectionKey key = it.next();
						it.remove();
						System.out.println("处理第" + (++eventCount) + "个事件");
						handleKey(key);
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void handleKey(SelectionKey key) throws IOException {
		if(key.isAcceptable()) {
			// 接收请求
			ServerSocketChannel server = (ServerSocketChannel) key.channel();
			SocketChannel channel = server.accept();
			channel.configureBlocking(false);
			channel.register(selector, SelectionKey.OP_READ);
		} else if(key.isReadable()) {
			// 读取请求的内容
			SocketChannel channel = (SocketChannel) key.channel();
			clientByteBuffer.clear();
			int count = 0;
			try {
				count = channel.read(clientByteBuffer);
				if(count > 0) {
					clientByteBuffer.flip();
					CharBuffer charBuffer = decoder.decode(clientByteBuffer);
					System.out.println("客户端>>" + charBuffer.toString());
				}
			} catch (Exception e) {
				// 客户端已经连接到服务端，但是客户端在发送请求前停止运行了
				key.cancel();
				channel.close();
			}
			
			channel.register(selector, SelectionKey.OP_WRITE).attach(new HandleClient());
		} else if(key.isWritable()) {
			// 对客户端请求进行响应
			SocketChannel channel = (SocketChannel) key.channel();
			HandleClient handleClient = (HandleClient) key.attachment();
			ByteBuffer result = handleClient.readBlock();
			
			if(null == result) {
				key.cancel();
				handleClient.close();
				channel.close();
			} else {
				// 处理方式的不一样会导致服务器性能指标值的不一样
//				do {
//					try {
//						Thread.currentThread().sleep(500);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					try {
//						channel.write(result);
//						result = handleClient.readBlock();
//					} catch (Exception e) {
//						handleClient.close();
//						channel.close();
//					}
//				} while(result != null);
				try {
					Thread.currentThread().sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				try {
					channel.write(result);
				} catch (Exception e) {
					handleClient.close();
					channel.close();
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		NIOFileServer nioFileServer = new NIOFileServer(8088);
		nioFileServer.listen();
	}

}

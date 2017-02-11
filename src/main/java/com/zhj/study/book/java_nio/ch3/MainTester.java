package com.zhj.study.book.java_nio.ch3;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class MainTester {

	public static void main(String[] args) throws Exception {
		testMutiVectorChanel();
	}
	
	/**
	 * 测试Chanel的双向性
	 * 结果：和底层的IO服务相关，文件通道是单向的
	 */
	private static void testMutiVectorChanel() throws Exception {
		FileInputStream fis = new FileInputStream("D:/test.log");
		FileChannel fc = fis.getChannel();
		ByteBuffer bf = ByteBuffer.allocate(1000);
		int count = 0;
		StringBuffer sb = new StringBuffer();
		
		Charset charset = Charset.forName("GBK");
		CharsetDecoder decoder = charset.newDecoder();
		
		while((count = fc.read(bf)) > 0) {
			bf.flip();
			CharBuffer charBuffer = decoder.decode(bf);
			System.out.println(charBuffer.toString());
		}
		
		// 报异常，因为FileChannel底层是单向的，虽然API有双向接口，即read和write方法
//		fc.write(bf);
	}

}

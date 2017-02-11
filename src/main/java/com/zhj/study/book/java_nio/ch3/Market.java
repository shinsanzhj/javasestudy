package com.zhj.study.book.java_nio.ch3;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Market {

	public static void main(String[] args) throws Exception {
		FileOutputStream fos = new FileOutputStream("D:/market.txt");
		
		FileChannel fc = fos.getChannel();
		
		ByteBuffer[] bfs = new ByteBuffer[5];
		bfs[0] = ByteBuffer.wrap("哈哈哈哈\n".getBytes("GBK"));
		bfs[1] = ByteBuffer.wrap("巴巴巴巴\n".getBytes("GBK"));
		bfs[2] = ByteBuffer.wrap("对滴对滴\r".getBytes("GBK"));
		bfs[3] = ByteBuffer.wrap("反反复复\r".getBytes("GBK"));
		bfs[4] = ByteBuffer.wrap("呜呜呜呜".getBytes("GBK"));
		
		while((fc.write(bfs)) > 0) {
			
		}
		
		fc.close();
		fos.close();
	}

}

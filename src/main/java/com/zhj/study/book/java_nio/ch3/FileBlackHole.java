package com.zhj.study.book.java_nio.ch3;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 测试文件黑洞
 * @author zhj
 *
 */
public class FileBlackHole {

	public static void main(String[] args) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(new File("D:/market.txt"), "rw");
		FileChannel fc = raf.getChannel();
		System.out.println(fc.size());
		System.out.println(fc.position());
//		ByteBuffer byteBuf = ByteBuffer.allocate(1);
		ByteBuffer byteBuf = ByteBuffer.wrap("aaaaaaaaaaaabbbbbbbbbbbbbbb".getBytes());
		fc.position(60);
		fc.write(byteBuf);
		fc.close();
	}
}

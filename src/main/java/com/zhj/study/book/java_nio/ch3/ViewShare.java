package com.zhj.study.book.java_nio.ch3;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 不同通道之间对应的文件视图都一致，一个通道改变文件内容，对另外一个通道可见
 * @author zhj
 *
 */
public class ViewShare {

	public static void main(String[] args) throws IOException {
		File file = new File("D:/market.txt");
		ByteBuffer byteBuf = ByteBuffer.wrap("wxyz".getBytes());
		ByteBuffer targetBuf1 = ByteBuffer.allocate(6);
		ByteBuffer targetBuf2 = ByteBuffer.allocate(6);
		
		// randomAccessFile
		RandomAccessFile raf1 = new RandomAccessFile(file, "rw");
		// 一个文件通道
		FileChannel fc1 = raf1.getChannel();
		
		// randomAccessFile
		RandomAccessFile raf2 = new RandomAccessFile(file, "rw");
		// 一个文件通道
		FileChannel fc2 = raf2.getChannel();
		
		fc1.read(targetBuf1);
		fc2.read(targetBuf2);
		fc1.position(0);
		fc2.position(0);
		//输出：fc1:012345,fc2:012345
		System.out.println("fc1:" + new String(targetBuf1.array()) + ",fc2:" + new String(targetBuf2.array()));
		
		fc1.write(byteBuf);
		
		targetBuf1.clear();
		targetBuf2.clear();
		fc1.position(0);
		fc2.position(0);
		
		fc1.read(targetBuf1);
		fc2.read(targetBuf2);
		//输出：fc1:wxyz45,fc2:wxyz45
		System.out.println("fc1:" + new String(targetBuf1.array()) + ",fc2:" + new String(targetBuf2.array()));
	}
}

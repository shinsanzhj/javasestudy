package com.zhj.study.book.java_nio.ch3;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 期待结果：
 * FileChannel和RandomAccessFile的position是从底层的文件描述符中获得的
 * 一个文件对应一个文件描述符
 * 所以一个FileChannel改变了position属性，对应相同文件的其他FileChannel或者RandomAccessFile的position属性也会被改变
 * 实际结果：
 * 同一个RandomAccessFile和对应的FileChannel才会从同一个文件描述符中获得position属性
 * @author zhj
 *
 */
public class PositionShare {

	public static void main(String[] args) throws IOException {
		File file = new File("D:/market.txt");
		
		// randomAccessFile
		RandomAccessFile raf1 = new RandomAccessFile(file, "rw");
		// 一个文件通道
		FileChannel fc1 = raf1.getChannel();
		
		// randomAccessFile
		RandomAccessFile raf2 = new RandomAccessFile(file, "rw");
		// 一个文件通道
		FileChannel fc2 = raf2.getChannel();
		
		System.out.println("file1:" + raf1.getFilePointer() + ",channel1:" + fc1.position() + ",file2:" + raf2.getFilePointer() + ",channel2:" + fc2.position());
		raf1.seek(30);
		System.out.println("file1:" + raf1.getFilePointer() + ",channel1:" + fc1.position() + ",file2:" + raf2.getFilePointer() + ",channel2:" + fc2.position());
		fc1.position(10);
		System.out.println("file1:" + raf1.getFilePointer() + ",channel1:" + fc1.position() + ",file2:" + raf2.getFilePointer() + ",channel2:" + fc2.position());
	}
}

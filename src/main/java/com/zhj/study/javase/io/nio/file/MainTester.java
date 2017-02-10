package com.zhj.study.javase.io.nio.file;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class MainTester {

	private static void testFileMap() throws Exception {
		RandomAccessFile rFile = new RandomAccessFile("D:/easyframe/test_performance.data", "rw");
		FileChannel fc = rFile.getChannel();
		MappedByteBuffer mbBuffer = fc.map(FileChannel.MapMode.READ_WRITE, 0, 0x4000000);
		for(int i = 0; i < 0x4000000; i++) {
			if((i % 100) == 0) {
				mbBuffer.put((byte) '\n');
			} else {
				mbBuffer.put((byte) 'X');
			}
			
		}
		fc.close();
		System.out.println("文件写入完毕");
	}
	
	private static void testLockFile() throws Exception {
		File file = new File("D:/easyframe/test.data");
		RandomAccessFile rFile = new RandomAccessFile(file, "rw");
		FileChannel fc = rFile.getChannel();
		FileLock lock = fc.lock();
		fc.write(ByteBuffer.wrap("hahahaha".getBytes()));
		lock.release();
		fc.close();
	}
	
	public static void main(String[] args) throws Exception {
//		testFileMap();
		testLockFile();
	}
}

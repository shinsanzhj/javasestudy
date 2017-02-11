package com.zhj.study.javase.io.nio.file.map;

import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

public class MainTester {

	private static void testReadFileMap() throws Exception {
		File file = new File("D:/easyframe/test.data");
		FileInputStream fis = new FileInputStream(file);
		FileChannel fc = fis.getChannel();
		MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0, 100);
		while(mbb.hasRemaining()) {
			System.out.print((char)mbb.get());
		}
	}
	
	private static void testWriteFileMap() throws Exception {
		File file = new File("D:/easyframe/test.data");
		RandomAccessFile rFile = new RandomAccessFile(file, "rw");
		FileChannel fc = rFile.getChannel();
		MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, 0, 100);
		mbb.put(3, (byte)' ');
		// write执行完毕，文件就已经写入了
		fc.write(mbb);
		System.out.println("写入文件完毕");
	}
	
	// 测试普通NIO读取文件的性能【堆内缓冲区】
	private static void testReadFilePerformance_Nio_Normal() throws Exception {
		long startTime = System.currentTimeMillis();
		File file = new File("D:/easyframe/test_performance.data");
		FileInputStream fis = new FileInputStream(file);
		FileChannel fc = fis.getChannel();
		
		// 在堆中分配了1024字节的缓冲区
		ByteBuffer bbf = ByteBuffer.allocate((int)fc.size());//29ms-64ms
//		ByteBuffer bbf = ByteBuffer.allocate(1024);//5ms-187ms
		long endTime1 = System.currentTimeMillis();
		System.out.println("内存分配消耗：" + (endTime1 - startTime) + "ms");
		int count = 0;
		while((count = fc.read(bbf)) > 0) {
			bbf.flip();
			bbf.clear();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("数据拷贝消耗时间：" + (endTime - endTime1) + "ms");
	}
	
	// 测试普通NIO读取文件的性能【直接内存缓冲区】
	private static void testReadFilePerformance_Nio_Normal_DirectMemery() throws Exception {
		long startTime = System.currentTimeMillis();
		File file = new File("D:/easyframe/test_performance.data");
		FileInputStream fis = new FileInputStream(file);
		FileChannel fc = fis.getChannel();
		
		// 在直接内存中分配了文件大小的缓冲区
		ByteBuffer bbf = ByteBuffer.allocateDirect((int)fc.size());//27ms-24ms
		// 在直接内存中分配了1024字节的缓冲区
//		ByteBuffer bbf = ByteBuffer.allocateDirect(1024);//5ms-113ms
		long endTime1 = System.currentTimeMillis();
		System.out.println("内存分配消耗：" + (endTime1 - startTime) + "ms");
		int count = 0;
		while((count = fc.read(bbf)) > 0) {
			bbf.flip();
			bbf.clear();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("数据拷贝消耗时间：" + (endTime - endTime1) + "ms");
	}
	
	// 测试NIO的内存映射方式读取文件的性能
	private static void testReadFilePerformance_Nio_Map() throws Exception {
		long startTime = System.currentTimeMillis();
		File file = new File("D:/easyframe/test_performance.data");
		FileInputStream fis = new FileInputStream(file);
		FileChannel fc = fis.getChannel();
		
		// 并没有分配缓冲区，而是和文件建立起映射关系
		MappedByteBuffer mbb = fc.map(MapMode.READ_ONLY, 0, fc.size());
		
		long endTime1 = System.currentTimeMillis();
		System.out.println("内存分配消耗：" + (endTime1 - startTime) + "ms");//6ms

		byte[] buf = new byte[1024];
		int length = (int) fc.size();
		for(int offset = 0; offset < length; offset += 1024) {
			if(length - offset > 1024) {
				mbb.get(buf);
			} else {
				mbb.get(new byte[length - offset]);
			}
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("数据拷贝消耗时间：" + (endTime - endTime1) + "ms");//21ms
	}
	
	/**
	 * @param args
	 *
	 * <pre>
	 * 修改日期		修改人	修改原因
	 * 2017-2-9		钟华杰	新建
	 * </pre>
	 */
	public static void main(String[] args) throws Exception {
//		testReadFileMap();
//		testWriteFileMap();
		testReadFilePerformance_Nio_Normal();
//		testReadFilePerformance_Nio_Map();
//		testReadFilePerformance_Nio_Normal_DirectMemery();
	}
}

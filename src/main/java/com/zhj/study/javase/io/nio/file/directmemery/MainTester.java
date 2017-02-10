package com.zhj.study.javase.io.nio.file.directmemery;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class MainTester {

	private static int MB = 1024 * 1024;
	
	// -Xmx64M：异常java.lang.OutOfMemoryError: Direct buffer memory
	// -Xmx64M -XX:MaxDirectMemorySize=200M：不会报异常了
	private static void testDirectBufferMemerySizeLimit() {
		ByteBuffer.allocateDirect(100 * MB);
	}
	
	// -Xmx64M -XX:MaxDirectMemorySize=50M -XX:+PrintGCDetails
	// 结论：直接内存快满了，会触发Full GC，这时jvm会释放没有被变量引用的直接内存，如果空间还是不够，那么就会报java.lang.OutOfMemoryError: Direct buffer memory
	private static void testDirectBufferMemeryGC() {
		System.out.println("第一次申请20MB直接内存");
		ByteBuffer tmp1 = ByteBuffer.allocateDirect(20 * MB);
		System.out.println("第二次申请20MB直接内存");
		ByteBuffer tmp2 = ByteBuffer.allocateDirect(20 * MB);
		System.out.println("第三次申请20MB直接内存");
		ByteBuffer tmp3 = ByteBuffer.allocateDirect(20 * MB);
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
//		testDirectBufferMemerySizeLimit();
//		testDirectBufferMemeryGC();
		testLockFile();
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
}

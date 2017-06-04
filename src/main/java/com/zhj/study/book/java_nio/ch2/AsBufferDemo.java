package com.zhj.study.book.java_nio.ch2;

import java.lang.ref.ReferenceQueue;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * byteBuffer.asIntBuffer
 * 得到的虽然是一个Int缓冲区，但和原来的byteBuffer对象公用的是同一个缓冲区
 * @author zhj
 *
 */
public class AsBufferDemo {

	public static void main(String[] args) {
		ByteBuffer bb = ByteBuffer.allocate(16);
		IntBuffer ib = bb.asIntBuffer();
		
		System.out.println(bb.limit());
		System.out.println(ib.limit());
		
		ib.put(3);
		bb.put((byte) 2);
		
		ib.flip();
		bb.flip();
		
		System.out.println(ib.limit());
		System.out.println(bb.limit());
		
		System.out.println(ib.get());
		System.out.println(bb.get());
		
		ReferenceQueue<T>
	}

}

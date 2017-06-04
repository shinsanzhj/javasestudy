package com.zhj.study.book.java_nio.ch3;

import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class ChannelCopy {

	public static void main(String[] args) throws Exception {
		ReadableByteChannel srcChannel = Channels.newChannel(System.in);
		WritableByteChannel targetChannel = Channels.newChannel(System.out);
		// 两种排干缓冲区的方式
		channelCopy1(srcChannel, targetChannel);
//		channelCopy2(srcChannel, targetChannel);
		srcChannel.close();
		targetChannel.close();
	}

	// 性能比较高的写法
	private static void channelCopy1(ReadableByteChannel srcChannel, WritableByteChannel targetChannel) throws Exception {
		// 创建10个字节的缓冲区
		ByteBuffer bf = ByteBuffer.allocate(10);
		
		while(srcChannel.read(bf) != -1) {
			bf.flip();
			// 不能保证bf中的有效内容一次性全部被写到通道中
			targetChannel.write(bf);
			// 防止bf中的内容没有一次性排干，对未排干的内容进行压缩[批量移动]
			bf.compact();
		}
		
		System.out.println("读到流结尾处");
		
		bf.flip();
		while(bf.hasRemaining()) {
			targetChannel.write(bf);
		}
	}
	
	// 和上一个方法相比，write的系统调用次数比较多
	private static void channelCopy2(ReadableByteChannel srcChannel, WritableByteChannel targetChannel) throws Exception {
		ByteBuffer bf = ByteBuffer.allocate(10);
		
		while(srcChannel.read(bf) != -1) {
			bf.flip();
			// 保证bf中的内容全部写到通道之后，再进行新数据的读取
			while(bf.hasRemaining()) {
				targetChannel.write(bf);
			}
			bf.clear();
		}
	}
	
}

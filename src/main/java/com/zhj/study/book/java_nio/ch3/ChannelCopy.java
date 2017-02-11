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
//		channelCopy1(srcChannel, targetChannel);
		channelCopy2(srcChannel, targetChannel);
		srcChannel.close();
		targetChannel.close();
	}

	private static void channelCopy1(ReadableByteChannel srcChannel, WritableByteChannel targetChannel) throws Exception {
		ByteBuffer bf = ByteBuffer.allocate(10);
		
		while(srcChannel.read(bf) != -1) {
			bf.flip();
			targetChannel.write(bf);
			bf.compact();
		}
		
		bf.flip();
		while(bf.hasRemaining()) {
			targetChannel.write(bf);
		}
	}
	
	private static void channelCopy2(ReadableByteChannel srcChannel, WritableByteChannel targetChannel) throws Exception {
		ByteBuffer bf = ByteBuffer.allocate(10);
		
		while(srcChannel.read(bf) != -1) {
			bf.flip();
			while(bf.hasRemaining()) {
				targetChannel.write(bf);
			}
			bf.clear();
		}
	}
	
}

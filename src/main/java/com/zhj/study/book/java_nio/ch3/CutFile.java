package com.zhj.study.book.java_nio.ch3;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * 利用FileChannel的truncate方法裁剪文件
 * @author zhj
 *
 */
public class CutFile {

	public static void main(String[] args) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(new File("D:/market.txt"), "rw");
		FileChannel fc = raf.getChannel();
		
		// 原文件大小
		System.out.println("before_size:" + fc.size());
		System.out.println("before_position:" + fc.position());
		
		fc.truncate(15);
		// 新文件大小
		System.out.println("before_size:" + fc.size());
		System.out.println("before_position:" + fc.position());
		
		fc.truncate(5);
		// 新文件大小
		System.out.println("before_size:" + fc.size());
		System.out.println("before_position:" + fc.position());
	}

}

package com.zhj.study.javase.charset;

public class MainTester {

	public static void main(String[] args) throws Exception {
//		testTemp();
		testGBK2UTF8();
	}
	
	private static void testGBK2UTF8() throws Exception {
		byte[] strGbkBytes = "你".getBytes("GBK");
		printByteArr(strGbkBytes);
		
		String utf8Str = new String(strGbkBytes, "UTF-8");
		System.out.println(utf8Str);
		printByteArr(utf8Str.getBytes("UTF-8"));
		printByteArr(utf8Str.getBytes("GBK"));
	}
	
	private static void testTemp() throws Exception {
		printByteArr("".getBytes());
		printByteArr("严".getBytes("GBK"));
		printByteArr("严".getBytes("UTF-8"));
		System.out.println(new String(new String("严".getBytes("GBK"), "UTF-8").getBytes("UTF-8"), "GBK"));
		System.out.println(new String(new String("严".getBytes("UTF-8"), "GBK").getBytes("GBK"), "UTF-8"));
	}
	
	private static void printByteArr(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toHexString(bytes[i] & 0xFF)).append(" ");
//			sb.append(bytes[i]).append(" ");
		}
		System.out.println(sb.substring(0, sb.length() - 1).toUpperCase());
	}
}

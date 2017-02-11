package com.zhj.study.javase.charset;

public class MainTester {

	/**
	 * 
	 */
	public static void main(String[] args) throws Exception {
		testCharToByte();
//		printByteArr("严".getBytes("GBK"));
//		printByteArr(new String("严".getBytes("GBK"), "ISO-8859-1").getBytes("ISO-8859-1"));
//		printByteArr(new String("严".getBytes("GBK"), "UTF-8").getBytes("UTF-8"));
//		testTemp();
//		testGBK2UTF8();
	}

	private static void testCharToByte() {
		String str = "淘";
		printBytes(str.getBytes());
	}
	
	private static void printBytes(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for(byte b : bytes) {
			sb.append(Integer.toHexString(b)).append("-");
		}
		System.out.println(sb.toString().substring(0, sb.toString().length() - 1));
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
		printByteArr("严".getBytes());
		printByteArr("严".getBytes("GBK"));
		printByteArr("严".getBytes("UTF-8"));
		System.out.println(new String(new String("很屌".getBytes("GBK"), "UTF-8").getBytes("UTF-8"), "GBK"));
		// 奇数个汉字、偶数个汉字【测试失真】
		// GBK2字节、UTF-8一般3字节
//		System.out.println(new String("严".getBytes("GBK"), "ISO-8859-1"));
		System.out.println(new String(new String("严".getBytes("UTF-8"), "ISO-8859-1").getBytes("ISO-8859-1"), "UTF-8"));
		System.out.println(new String(new String("严".getBytes("GBK"), "ISO-8859-1").getBytes("ISO-8859-1"), "GBK"));
		System.out.println(new String(new String("严".getBytes("UTF-8"), "GBK").getBytes("GBK"), "UTF-8"));
		System.out.println(new String(new String("严严".getBytes("UTF-8"), "GBK").getBytes("GBK"), "UTF-8"));
		System.out.println(new String(new String("严严严".getBytes("UTF-8"), "GBK").getBytes("GBK"), "UTF-8"));
		
		System.out.println(new String(new String("严".getBytes("GBK"), "UTF-8").getBytes("UTF-8"), "GBK"));
		System.out.println(new String(new String("严严".getBytes("GBK"), "UTF-8").getBytes("UTF-8"), "GBK"));
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

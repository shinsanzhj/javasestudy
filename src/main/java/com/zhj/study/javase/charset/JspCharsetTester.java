package com.zhj.study.javase.charset;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Properties;

public class JspCharsetTester {
	/**
	 * @param args
	 *
	 * <pre>
	 * 修改日期		修改人	修改原因
	 * 2016-10-27	钟华杰	新建
	 * </pre>
	 */
	public static void main(String[] args) throws Exception {
//		testEncodeLength();
//		testEncodeAndDecode();
//		testUrlEncodeAnDecode();
		testEacheEncodeAndRecover();
	}
	
	private static void testEacheEncodeAndRecover() throws Exception {
		String str = "登录";
		/**
		 * JSP文件存储编码
		 */
		byte[] strBytes = str.getBytes("UTF-8");
		/**
		 * JSP内容编译成JAVA文件输出流编码（out.write("")中的内容）（pageEncoding决定，如果没设置）
		 * 1.如果设置了pageEncoding，则以pageEncoding来进行编码
		 * 2.如果没有设置，则默认以ISO-8859-1来编码（Tomcat中可以配置该默认值）
		 */
		String newStr = new String(strBytes, "ISO-8859-1");
		System.out.println(newStr);
		/**
		 * 传输时编码（JSP中设置的ContentType）
		 * 1.如果有设置ContentType编码，则以ContentType中的编码为准
		 * 2.如果没有设置ContentType编码，但是有设置pageEncoding编码，则以pageEncoding编码为准
		 * 3.如果都没有设置，则默认以ISO-8859-1来编码
		 */
		byte[] strTranslation = newStr.getBytes("ISO-8859-1");
		/**
		 * 浏览器显示页面时编码
		 * 1.ContentType中有设置编码，则以ContentType中的编码来显示
		 * 2.ContentType中没有设置编码，<meta>标签中有设置编码，则以<meta>中设置的编码来显示
		 * 3.如果以上两处都没设置编码，则以客户端操作系统编码来显示（windows中文操作系统编码：GBK）
		 */
		System.out.println(new String(strTranslation, "GBK"));
	}
	
	private static void testEncodeLength() throws Exception {
		String str = "严";
		printByte(str.getBytes("GBK"));
		printByte(str.getBytes("GB2312"));
		printByte(str.getBytes("UTF-8"));
		printByte(str.getBytes("ISO-8859-1"));
	}
	
	private static void testEncodeAndDecode() throws Exception {
		System.out.println(new String("严".getBytes("ISO-8859-1"), "ISO-8859-1"));
		System.out.println(new String("严".getBytes("GBK"), "UTF-8"));
		//GBK是GB2312的扩展
		System.out.println(new String("严".getBytes("GBK"), "GB2312"));
		System.out.println(new String("嚴".getBytes("GBK"), "GB2312"));
	}
	
	private static void testUrlEncodeAnDecode() throws Exception {
//		printSystemProperties();
		System.out.println(URLEncoder.encode("百度严"));
		System.out.println(URLEncoder.encode("百度严", "ISO-8859-1"));
		System.out.println(URLEncoder.encode("百度严", "GBK"));
		System.out.println(URLEncoder.encode("百度严", "UTF-8"));
		
		System.out.println(URLDecoder.decode("%B0%D9%B6%C8%D1%CF", "GBK"));
		
		//前端JS：encodeURI("百度严")
		System.out.println(URLDecoder.decode("%E7%99%BE%E5%BA%A6%E4%B8%A5", "UTF-8"));
		
		//前端JS：encodeURI(encodeURI("百度严"))
		System.out.println(URLDecoder.decode(URLDecoder.decode("%25E7%2599%25BE%25E5%25BA%25A6%25E4%25B8%25A5", "ISO-8859-1"), "UTF-8"));
		System.out.println(URLDecoder.decode(URLDecoder.decode("%25E7%2599%25BE%25E5%25BA%25A6%25E4%25B8%25A5", "GBK"), "UTF-8"));
		System.out.println(URLDecoder.decode(URLDecoder.decode("%25E7%2599%25BE%25E5%25BA%25A6%25E4%25B8%25A5"), "UTF-8"));
	}
	
	private static void printSystemProperties() {
		Properties props = System.getProperties();
		Enumeration enumeration = props.propertyNames();
		System.out.println("\tKEY\t\t\tVALUE");
		while(enumeration.hasMoreElements()) {
			String key = (String) enumeration.nextElement();
			String value = props.getProperty(key);
			System.out.println(key + "\t\t" + value);
		}
	}
	
	private static void printByte(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for(byte b : bytes) {
			sb.append(b);
		}
		System.out.println("字节数：" + bytes.length + "[" + sb.toString() + "]");
	}
}

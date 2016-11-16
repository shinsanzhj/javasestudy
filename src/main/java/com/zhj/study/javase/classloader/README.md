自定义类加载器的用途
<p>1.用软件对class文件进行加密，运行的时候用自定义的ClassLoader进行解密，然后再加载到JVM中，防止别人反编译class文件来获取程序的运行逻辑。</p>

工作中涉及到的
	1.一个tomcat/lib目录中的类想访问（通过Class.forName()）tomcat/webapps/demo/WEB-INF/lib目录中的类，则需要用到线程类加载器。
	
参考资料：
	http://wenku.baidu.com/link?url=8vxb12ETlLejTwQUmHjIG6R3fjgzqIyK7lIZwbhi_BNk48vM-5pO-4MIdXkCVFWHxFpeO57Q1BjXvXus5jtSgHmDfbchf7s9wcB0LQ0ALqq
	http://blog.csdn.net/joeyshi/article/details/4138483

	

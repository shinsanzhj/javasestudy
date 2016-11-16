# 自定义类加载器的用途
1.	用软件对class文件进行加密，运行的时候用自定义的ClassLoader进行解密，然后再加载到JVM中，防止别人反编译class文件来获取程序的运行逻辑。

# 工作中涉及点
1.	一个tomcat/lib目录中的类想访问（通过Class.forName()）tomcat/webapps/demo/WEB-INF/lib目录中的类，则需要用到线程类加载器。
	
# 参考资料：
1.	[运用加密技术保护Java源代码](http://wenku.baidu.com/link?url=8vxb12ETlLejTwQUmHjIG6R3fjgzqIyK7lIZwbhi_BNk48vM-5pO-4MIdXkCVFWHxFpeO57Q1BjXvXus5jtSgHmDfbchf7s9wcB0LQ0ALqq)
2.	[如何对java的class类进行加密](http://blog.csdn.net/joeyshi/article/details/4138483)

# 要点：
1.	类加载的步骤：装载（把class中的内容读取到jvm中，形成一个Class实例）、链接（对该Class实例中的静态变量进行初始化，并且解析该类依赖的类和接口）、初始化（执行静态初始化代码、静态属性的初始化）
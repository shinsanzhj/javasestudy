# 自定义类加载器的用途
1.	用软件对class文件进行加密，运行的时候用自定义的ClassLoader进行解密，然后再加载到JVM中，防止别人反编译class文件来获取程序的运行逻辑。

# 工作中涉及点
1.	一个tomcat/lib目录中的类想访问（通过Class.forName()）tomcat/webapps/demo/WEB-INF/lib目录中的类，则需要用到线程类加载器。
	
# 参考资料：
1.	[运用加密技术保护Java源代码](http://wenku.baidu.com/link?url=8vxb12ETlLejTwQUmHjIG6R3fjgzqIyK7lIZwbhi_BNk48vM-5pO-4MIdXkCVFWHxFpeO57Q1BjXvXus5jtSgHmDfbchf7s9wcB0LQ0ALqq)
2.	[如何对java的class类进行加密](http://blog.csdn.net/joeyshi/article/details/4138483)

# 要点：
		类加载的步骤：装载（把class中的内容读取到jvm中，形成一个Class实例）
		链接:（对该Class实例中的静态变量进行初始化，并且解析该类依赖的类和接口）
		初始化:（执行静态初始化代码、静态属性的初始化）
		
ADD BY 2017-04-07
# 一个类中如果引用了其他jar的类,比如Demo中的useEhcache方法用到了ehcache.jar中的类,但是运行环境中没有包含ehcache.jar,此时会报类找不到的错误;但是只要没有调用useEhcache这个方法,那么就不会报错。
# 所以，在Java中，引用类加载的时机是用到的时候才有加载，并不是说加载了主类，就会把主类中引用的所有其他类一起加载完毕，就是按需加载。
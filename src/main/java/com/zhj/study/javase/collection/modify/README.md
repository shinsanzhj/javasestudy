# 开两个线程，一个用Iterator，另一个用for循环，遍历同一个list。
	测试结果：Iterator的那个线程中途会对list进行移除元素操作，另外一个for循环会抛异常Exception in thread "Thread-0" java.util.ConcurrentModificationException
	
# 集合类有实现对应的迭代器,通过迭代器删除,能实现对应的删除动作
##	map的keys迭代器,通过删除keys中的key,能实现删除map中对应的key-value
##	map的values迭代器,通过删除values中的value,能实现删除map中对应的key-value
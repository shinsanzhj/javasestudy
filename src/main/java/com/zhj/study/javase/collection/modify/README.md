# 开两个线程，一个用Iterator，另一个用for循环，遍历同一个list。
	测试结果：Iterator的那个线程中途会对list进行移除元素操作，另外一个for循环会抛异常Exception in thread "Thread-0" java.util.ConcurrentModificationException
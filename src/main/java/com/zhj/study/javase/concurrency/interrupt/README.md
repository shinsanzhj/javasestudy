interrupt():设置线程的中断位为true。并不会中断线程，只是设置线程的“中断 ”状态。
interrupted():查询线程的中断位，并且清除中断位
isInterrupted():查询返回线程的中断位

知识点：
     1.线程的中断位为true时，如果再由wait，join，sleep三个方法引起的阻塞，那么jvm会重新将线程的中断位设置成false，并且抛出一个InterruptedException异常，开发人员就可以在catch块中执行自己的逻辑。
     2.由上面的知识点可知，如果手动调用了线程的interrupt()方法，然后再调用该线程的sleep方法，线程并不会进行睡眠，而是直接跳到catch块中。wait、join方法类似。
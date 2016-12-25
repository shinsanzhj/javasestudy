package com.zhj.study.javase.concurrency.material;

public class VolatileTest extends Thread {
    
    boolean flag = false;
    int i = 0;
    
    public void run() {
    	long startTime = System.currentTimeMillis();
        while (!flag) {
        	// 不能加上这句，可能IO密集型操作会让CPU有空去读取主内存的最新变量值
//        	System.out.println("子线程继续执行");
            i++;
        }
        System.out.println("耗时：" + ((System.currentTimeMillis() - startTime) / 1000) + "秒");
    }
    
    public static void main(String[] args) throws Exception {
        VolatileTest vt = new VolatileTest();
        vt.start();
        Thread.sleep(2000);
        vt.flag = true;
        System.out.println("stope" + vt.i);
    }
}

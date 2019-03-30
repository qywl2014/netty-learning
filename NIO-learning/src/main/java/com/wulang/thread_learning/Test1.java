package com.wulang.thread_learning;

import java.util.concurrent.*;

public class Test1 {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                new MyDefaultThreadFactory());
        for(int i=1;i<=4;i++){
            threadPoolExecutor.execute(new MyTask(i));
        }
        System.out.println("main over");
    }

    public static class MyTask implements Runnable{
        private int num;
        public MyTask(int num){
            this.num=num;
        }
        @Override
        public void run() {
            String threadName=Thread.currentThread().getName();
            System.out.println(num+":"+threadName);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

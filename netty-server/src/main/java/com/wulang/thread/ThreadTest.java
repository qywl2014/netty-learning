package com.wulang.thread;

public class ThreadTest {
    private static int count=0;
    public static void main(String[] args) throws Exception{
        for(int i=0;i<2;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i=0;i<1000000;i++){
                        count++;
                    }
                }
            }).start();
        }
        Thread.sleep(2000);
        System.out.println(count);
    }
}

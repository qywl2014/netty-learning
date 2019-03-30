package com.wulang.thread_learning.waittest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test1 {
    public static long max = Long.MAX_VALUE / 1000000000 / 10;

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();

//        for (int j = 0; j < 50; j++) {
//            c();
//        }
        for (int j = 0; j < 5; j++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i=0;i<10;i++){

                    c();
                    }
                }
            }).start();
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
//        System.out.println("size:"+list.size());
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("sleep over");
        }
    }

    static void c() {
        for (long i = 0; i < max; i++) {

        }
    }
}

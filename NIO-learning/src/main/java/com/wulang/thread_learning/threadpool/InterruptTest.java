package com.wulang.thread_learning.threadpool;

public class InterruptTest {
    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(100000);
                    } catch (InterruptedException e) {
//                        e.printStackTrace();
                    }
                    System.out.println("thread");
                }
            }
        });
        t.start();
        while (true) {
            try {
                Thread.sleep(2000);
                t.interrupt();
                System.out.println("interrupt");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

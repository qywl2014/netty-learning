package com.wulang.thread_learning.threadpool;

import java.util.Date;

public class Ordinary {
    public static void main(String[] args) {
        long startTime=new Date().getTime();
        for(int i=0;i<1000;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {

                }
            }).start();
        }
    }
}

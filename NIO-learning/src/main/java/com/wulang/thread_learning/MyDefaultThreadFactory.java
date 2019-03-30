package com.wulang.thread_learning;

import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.concurrent.ThreadFactory;

public class MyDefaultThreadFactory implements ThreadFactory {
    public static int count=100;

    @Override
    public Thread newThread(Runnable r) {
        System.out.println("-------");
        return new Thread(r,++count+"");
    }
}

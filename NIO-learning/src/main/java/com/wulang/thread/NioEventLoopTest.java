package com.wulang.thread;

import io.netty.channel.EventLoop;
import io.netty.channel.SingleThreadEventLoop;
import io.netty.channel.nio.NioEventLoop;

import java.util.concurrent.Executors;

public class NioEventLoopTest {
    public static void main(String[] args) {
        EventLoop eventLoop=new SingleThreadEventLoop(null,Executors.defaultThreadFactory(),false){
            @Override
            protected void run() {
                System.out.println("123");
            }
        };
        eventLoop.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("234");
            }
        });
    }
}

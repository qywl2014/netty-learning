package com.wulang.thread_learning.blockingqueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueTest implements Delayed {
    public DelayQueueTest(){
        
    }


    @Override
    public long getDelay(TimeUnit unit) {
        return 0;
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }

    public static void main(String[] args) {
        DelayQueue<DelayQueueTest> delayQueueTests=new DelayQueue<>();
    }
}

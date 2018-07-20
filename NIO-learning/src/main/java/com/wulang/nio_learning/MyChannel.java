package com.wulang.nio_learning;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class MyChannel {
    public static void main(String[] args) throws Exception {
        RandomAccessFile aFile = new RandomAccessFile("C:\\Users\\Administrator\\Desktop\\idea_new\\netty-learning\\NIO-learning\\src\\main\\resources\\hi.txt", "rw");
        FileChannel inChannel = aFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = inChannel.read(buf);
//        buf.position();
//        buf.limit();
//        System.out.println("position"+buf.position());
        int count=1;
        while (bytesRead != -1) {
            System.out.println("第"+count+"次");
            count++;
//            System.out.println("haha");
            System.out.println("Read " + bytesRead);
            buf.flip();
//            while(buf.hasRemaining()){
                System.out.print((char) buf.get());
//            }
            buf.compact();
            System.out.print((char) buf.get());
            System.out.print((char) buf.get());
            System.out.println();
            bytesRead = inChannel.read(buf);
//            System.out.println("position"+buf.position());
        }
        aFile.close();
    }
}


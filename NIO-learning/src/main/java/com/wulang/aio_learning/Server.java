package com.wulang.aio_learning;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class Server {
    public static void main(String[] args) throws Exception {
        final AsynchronousServerSocketChannel listener =
                AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(5000));

        listener.accept(null, new CompletionHandler<AsynchronousSocketChannel,Void>() {
            public void completed(AsynchronousSocketChannel ch, Void att) {
                // accept the next connection
                listener.accept(null, this);

                // handle this connection
                handle(ch);
            }
            public void failed(Throwable exc, Void att) {
                exc.printStackTrace();
            }
        });

        Object lock = new Object();
        synchronized (lock){
            lock.wait();
        }
    }

    private static void handle(final AsynchronousSocketChannel ch){
        final AsynchronousSocketChannel chLocal = ch;
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        ch.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                ByteBuffer newByteBuffer = ByteBuffer.allocate(1024);
                chLocal.read(newByteBuffer,newByteBuffer,this);

                read(attachment);
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                exc.printStackTrace();
            }
        });
    }

    private static void read(ByteBuffer attachment){
        System.out.println(new String(attachment.array()));
    }
}

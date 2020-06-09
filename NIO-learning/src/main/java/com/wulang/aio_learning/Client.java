package com.wulang.aio_learning;

import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        AsynchronousSocketChannel asynchronousSocketChannel = AsynchronousSocketChannel.open();
        asynchronousSocketChannel.connect(new InetSocketAddress("127.0.0.1", 5000), asynchronousSocketChannel, new CompletionHandler<Void, AsynchronousSocketChannel>() {
            @Override
            public void completed(Void result, AsynchronousSocketChannel attachment) {
                attachment.write(ByteBuffer.wrap("async".getBytes()));
            }

            @Override
            public void failed(Throwable exc, AsynchronousSocketChannel attachment) {

            }
        });

        Object lock = new Object();

        Scanner scanner = new Scanner(System.in);
        while (true){
            String input = scanner.nextLine();
            System.out.println(input);
            asynchronousSocketChannel.write(ByteBuffer.wrap(input.getBytes()));
        }

    }
}

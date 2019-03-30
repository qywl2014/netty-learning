package com.wulang.nio_learning;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Client {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        socketChannel.connect(new InetSocketAddress("localhost",6666));
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        for(;;){
            selector.select();
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();
            Iterator<SelectionKey> iterator=selectionKeySet.iterator();
            while(iterator.hasNext()){
                SelectionKey selectionKey=iterator.next();
                iterator.remove();
                if(selectionKey.isConnectable()){
                    System.out.println("connectable");
                    SocketChannel sc=(SocketChannel) selectionKey.channel();
                    if(sc.finishConnect()){
//                        sc.register(selector,SelectionKey.OP_WRITE);
                        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
                        byteBuffer.put("hello wulang nio server".getBytes());
                        byteBuffer.flip();
                        sc.write(byteBuffer);
                    }
                }
                if(selectionKey.isWritable()){
                    System.out.println("writable");
//                    SocketChannel sc=(SocketChannel) selectionKey.channel();
//                    ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
//                    byteBuffer.put("hello wulang nio server".getBytes());
//                    byteBuffer.flip();
//                    sc.write(byteBuffer);
                }
            }
        }

    }
}


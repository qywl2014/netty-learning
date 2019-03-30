package com.wulang.nio_learning;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        for(;;){
            selector.select();
            Set<SelectionKey> set = selector.selectedKeys();
            Iterator<SelectionKey> iterator=set.iterator();
            while(iterator.hasNext()){
                SelectionKey selectionKey=iterator.next();
                iterator.remove();
                if(selectionKey.isAcceptable()){
                    System.out.println("accetp 1 client");
                    ServerSocketChannel ssc=(ServerSocketChannel) selectionKey.channel();
                    SocketChannel client=ssc.accept();
                    client.configureBlocking(false);
                    client.register(selector,SelectionKey.OP_READ);
                }
                if(selectionKey.isReadable()){
                    System.out.println("readble");
                    SocketChannel sc=(SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
                    sc.read(byteBuffer);
//                    String result=byteBuffer.toString();//试一下会怎样
                    byteBuffer.flip();
                    byte[] data=new byte[byteBuffer.remaining()];
                    byteBuffer.get(data);
                    System.out.println(new String(data, StandardCharsets.UTF_8));
                }
            }
        }

    }

}


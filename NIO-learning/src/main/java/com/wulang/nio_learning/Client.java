package com.wulang.nio_learning;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class Client {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        socketChannel.connect(new InetSocketAddress("localhost",6666));
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        selector.select();
        Set<SelectionKey> selectionKeySet = selector.selectedKeys();
        System.out.println(selectionKeySet.size());
        SelectionKey selectionKey = selectionKeySet.iterator().next();
        selectionKey.channel();
    }
}


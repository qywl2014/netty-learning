package com.wulang.nio_learning;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Set;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(6666));
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        selector.select();
        Set<SelectionKey> set = selector.selectedKeys();
        for (SelectionKey selectionKey : set) {
            if (selectionKey.isAcceptable()) {
                System.out.println("accept");
            }
        }
    }

}


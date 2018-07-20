package com.wulang;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Unit test for simple App.
 */
public class NIOTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void transferTest() throws Exception {
        RandomAccessFile fromFile = new RandomAccessFile("C:\\Users\\Administrator\\Desktop\\idea_new\\netty-learning\\NIO-learning\\src\\main\\resources\\ok.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("C:\\Users\\Administrator\\Desktop\\idea_new\\netty-learning\\NIO-learning\\src\\main\\resources\\hi.txt", "rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 4;
        long count = fromChannel.size();

        toChannel.transferFrom(fromChannel, position, count);
    }

    @Test
    public void selectorTest() throws Exception {
        Selector selector = Selector.open();
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);

        SelectionKey key = channel.register(selector, SelectionKey.OP_READ);


        while (true) {

            int readyChannels = selector.select();

            if (readyChannels == 0) continue;


            Set<SelectionKey> selectedKeys = selector.selectedKeys();

            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

            while (keyIterator.hasNext()) {

                SelectionKey key1 = keyIterator.next();

                if (key1.isAcceptable()) {
                    // a connection was accepted by a ServerSocketChannel.

                } else if (key1.isConnectable()) {
                    // a connection was established with a remote server.

                } else if (key1.isReadable()) {
                    // a channel is ready for reading

                } else if (key1.isWritable()) {
                    // a channel is ready for writing
                }

                keyIterator.remove();
            }
        }
    }

    @Test
    public void socketChannelTest() throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("http://jenkov.com", 80));
        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = socketChannel.read(buf);
        System.out.println(buf.toString());
        socketChannel.close();
    }




}

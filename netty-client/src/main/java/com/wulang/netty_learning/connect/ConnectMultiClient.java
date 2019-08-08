package com.wulang.netty_learning.connect;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.oio.OioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectMultiClient {

    public static void main(String[] args) throws Exception {
        createServer(1001);
        createServer(1002);

        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Bootstrap b = new Bootstrap();
        b.group(new OioEventLoopGroup())
                .channel(OioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                ByteBuf byteBuf = (ByteBuf) msg;
                                System.out.println("bootstarp receive:"+byteBuf.toString(Charset.defaultCharset()));
                                ctx.fireChannelRead(msg);
                            }
                        });
                    }
                });
        // DefaultChannelPileline.HeadContext.read(ChannelHandlerContext ctx)开始接收消息
        // channel大多数方法比如bind，connect都是调用pipeline的
        // connect成功之后HeadContext.channelActive触发从tailcontext往前所有outbound的read直到headcontext
        // 读完一次后HeadContext.channelReadComplete也跟channelActive一样调用readIfIsAutoRead
        // 创建channel,创建属于该channel的pipeline
        // sync是调用channelFuture的wait()，connect()成功之后会调用channelFuture的notifyAll()
        ChannelFuture channelFuture = b.connect("127.0.0.1", 1001).sync();
        ByteBuf byteBuf = Unpooled.copiedBuffer("123qw", Charset.defaultCharset());
        channelFuture.channel().writeAndFlush(byteBuf).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("success...");
                } else {
                    System.out.println("fail...");
                    System.out.println(future.cause());
                }
            }
        });
    }

    private static void createServer(final int port) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ExecutorService executorService = Executors.newFixedThreadPool(3);
                    ServerSocket serverSocket = new ServerSocket(port);
                    for (; ; ) {
                        final Socket socket = serverSocket.accept();
                        System.out.println(socket);
                        executorService.execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    InputStream inputStream = socket.getInputStream();
                                    OutputStream outputStream = socket.getOutputStream();
                                    for (; ; ) {
                                        byte[] bytes = new byte[500];
                                        inputStream.read(bytes, 0, 500);//就是不知道这里哪些情况回返回
                                        System.out.println(port+" receive:"+new String(bytes));
                                        outputStream.write("back".getBytes());
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}

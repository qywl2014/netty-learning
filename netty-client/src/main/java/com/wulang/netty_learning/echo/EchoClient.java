package com.wulang.netty_learning.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ReferenceCountUtil;

import java.net.InetSocketAddress;

public class EchoClient {

    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();                //1
            b.group(group)                                //2
                    .channel(NioSocketChannel.class)            //3
                    .remoteAddress(new InetSocketAddress(host, port))    //4
                    .handler(new ChannelInitializer<SocketChannel>() {    //5
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            ch.pipeline()
//                                    .addLast(new EchoClientOutBoundHandler("out001"))
                                    .addLast(new EchoClientHandler());
//                                    .addLast(new EchoClientOutBoundHandler("out002"));
                         }
                    });

            final ChannelFuture f = b.connect().sync();        //6
//            Channel channel=f.channel();
//            ByteBuf byteBuf=channel.alloc().buffer(4);
//            byteBuf.writeBytes("channal send".getBytes());

            f.channel().closeFuture().sync();            //7
        } finally {
//            group.shutdownGracefully().sync();            //8
        }
    }

    public static void main(String[] args) throws Exception {
        new EchoClient("localhost", 333).start();
//        new EchoClient("localhost", 6666).start();
    }
}

package com.wulang.echo.oldiowithnetty;

import com.wulang.echo.EchoServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;

public class OldIOServer {
    public static void main(String[] args) throws Exception {
        ServerBootstrap b=new ServerBootstrap();
        b.group(new OioEventLoopGroup())
                .channel(OioServerSocketChannel.class)
                .localAddress(1234)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch){
                        ch.pipeline().addLast(new EchoServerHandler("001"));
                    }
                });
        b.bind();
//        f.channel().closeFuture().sync();
    }
}

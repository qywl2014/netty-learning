package com.wulang.netty_learning.oldiowithnetty;

import com.wulang.netty_learning.echo.EchoClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.oio.OioSocketChannel;

public class OldIOClient {
    public static void main(String[] args) throws Exception{
        Bootstrap b=new Bootstrap();
        b.group(new OioEventLoopGroup())
                .channel(OioSocketChannel.class)
                .remoteAddress("",1)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new EchoClientHandler());
                    }
                });
        ChannelFuture f=b.connect().sync();
        f.channel().closeFuture().sync();
    }
}

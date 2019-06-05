package com.wulang.netty_learning.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

@ChannelHandler.Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        final ByteBuf time = ctx.alloc().buffer(4); // (2)
        time.writeBytes("hello".getBytes());
//        ctx.channel().write(time);
        ctx.write("he123");
        ctx.write(time);
        ctx.write("he123");
        ctx.writeAndFlush(time);
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx,
                             ByteBuf in) {
//        ctx.writeAndFlush(Unpooled.copiedBuffer("read0",CharsetUtil.UTF_8));
//        System.out.println("Client received: " + in.toString(CharsetUtil.UTF_8));    //3
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) {                    //4
        cause.printStackTrace();
        ctx.close();
    }
}


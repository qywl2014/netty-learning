package com.wulang.netty_learning.echo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.CharsetUtil;

public class EchoClientOutBoundHandler extends ChannelOutboundHandlerAdapter {
    private String name;

    public EchoClientOutBoundHandler(String name){
        this.name=name;
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println(name+"   outbound:"+((ByteBuf) msg).toString(CharsetUtil.UTF_8));
        ctx.write(msg, promise);
    }

    @Override
    public void flush(ChannelHandlerContext ctx) throws Exception {
        System.out.println(name+" flush");
        ctx.flush();
    }
}


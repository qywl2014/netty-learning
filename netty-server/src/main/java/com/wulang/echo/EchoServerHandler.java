package com.wulang.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

@ChannelHandler.Sharable                                        //1
public class EchoServerHandler extends
        ChannelInboundHandlerAdapter {

    private String name;

    public EchoServerHandler(String name){
        this.name=name;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf byteBuf=ctx.alloc().buffer();
        byteBuf.writeBytes("nihao\n666".getBytes());
        ctx.writeAndFlush(byteBuf);
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx,
                            Object msg) {
//        ByteBuf in = (ByteBuf) msg;
        System.out.println(this.name +" Server received: " + ((ByteBuf) msg).toString(CharsetUtil.UTF_8));//2
        ctx.fireChannelRead(msg);
//        ctx.write(in);
//        ctx.flush();         //3
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)//4
//                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) {
        cause.printStackTrace();                //5
        ctx.close();                            //6
    }
}

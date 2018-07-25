package com.wulang.http.myself;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.io.*;

public class HttpServer {
    public static void main(String[] args) throws Exception {
        EventLoopGroup nioEventLoopGroup=new NioEventLoopGroup();
        try {
            final ServerBootstrap serverBootstrap=new ServerBootstrap();
            serverBootstrap.group(nioEventLoopGroup)
                            .channel(NioServerSocketChannel.class)
                            .localAddress(666)
                            .childHandler(new ChannelInitializer<SocketChannel>() {
                                @Override
                                public void initChannel(SocketChannel socketChannel){
                                    socketChannel.pipeline().addLast(new HttpServerCodec());
                                    socketChannel.pipeline().addLast(new HttpObjectAggregator(10*1024*1024));
                                    socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                                        @Override
                                        public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception{
                                            FullHttpResponse fullHttpResponse=new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.OK,
                                                    Unpooled.copiedBuffer(readFile("C:\\Users\\Administrator\\Desktop\\idea_new\\netty-learning\\netty-http-server\\src\\main\\resources\\index.html"),CharsetUtil.UTF_8));
                                            fullHttpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/html;charset=utf-8");
                                            ctx.writeAndFlush(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
                                        }
                                    });
                                }
                            });
            ChannelFuture channelFuture=serverBootstrap.bind().sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            nioEventLoopGroup.shutdownGracefully().sync();
        }
    }

    public static String readFile(String path) throws IOException {
        InputStream is = new FileInputStream(path);
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuffer buffer = new StringBuffer();
        String line;
        while ((line = in.readLine()) != null){
            buffer.append(line);
        }
        return buffer.toString();

    }
}


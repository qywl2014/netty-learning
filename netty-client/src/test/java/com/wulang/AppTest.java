package com.wulang;

import static org.junit.Assert.assertTrue;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     *                                                      channel.alloc()获得
     *              ByteBufAllocator（有两种实现池化和未池化）:
     *                                                      channelHandlerContext.alloc()获得
     * ByteBuf分配:
     *
     *              Unpooled（创建未池化的ByteBuf实例）
     *
     * channel和channelPipeLine一一对应，不能改变
     *
     *
     * 每当channelHandler被添加到channelPipeLine都会创建channelHandlerContext
     * channelHandler可以从属于多个channelPipeLine,但必须标注@sharable否则会抛异常
     *
     * channelHandlerContext和channelHandler之间的绑定是永远不变的，缓存对它的引用是安全的
     *
     */
    @Test
    public void byteBufTest()
    {
        ByteBuf byteBuf=Unpooled.copiedBuffer("lalala",CharsetUtil.UTF_8);
        System.out.println(byteBuf.arrayOffset());
        System.out.println(byteBuf.readerIndex());
        System.out.println(byteBuf.writerIndex());
        for(int i=0;i<byteBuf.capacity();i++){
            System.out.print(byteBuf.readerIndex());
            System.out.println((char)byteBuf.getByte(i)+"m");
        }
        for(int i=0;i<byteBuf.capacity();i++){
            System.out.println((char)byteBuf.getByte(i));
        }
    }
}

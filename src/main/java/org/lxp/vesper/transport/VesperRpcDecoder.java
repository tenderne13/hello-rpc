package org.lxp.vesper.transport;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.lxp.vesper.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class VesperRpcDecoder extends ByteToMessageDecoder {
    private static final Logger log = LoggerFactory.getLogger(VesperRpcDecoder.class);

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        //org.lxp.vesper.protocol.Header 16字节
        if (byteBuf.readableBytes() < Constants.HEADER_SIZE) {
            log.info("decode => header 信息不足");
            return;
        }
        byteBuf.markReaderIndex();
        // 魔数
        short magicNum = byteBuf.readShort();
        if (magicNum != Constants.MAGIC_NUM) {
            byteBuf.resetReaderIndex();
            log.error("decode => 非法魔数 {}", magicNum);
            throw new RuntimeException("decode => 非法魔数");
        }


    }
}

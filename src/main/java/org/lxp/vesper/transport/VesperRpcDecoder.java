package org.lxp.vesper.transport;

import cn.hutool.json.JSONUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.lxp.vesper.constants.Constants;
import org.lxp.vesper.protocol.Header;
import org.lxp.vesper.protocol.Message;
import org.lxp.vesper.protocol.Request;
import org.lxp.vesper.serialization.HessionSerialization;
import org.lxp.vesper.serialization.SerializationFactory;
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
        // 附加信息 0 心跳响应
        byte extraInfo = byteBuf.readByte();
        // 消息ID
        long messageId = byteBuf.readLong();
        // 消息体长度
        int size = byteBuf.readInt();

        Object request = null;
        if (!Constants.isHeartBeat(extraInfo)) {
            //组装消息
            if (byteBuf.readableBytes() < size) {
                byteBuf.resetReaderIndex();
                return;
            }
            byte[] payload = new byte[size];
            byteBuf.readBytes(payload);

            //使用hessian反序列化
            request = SerializationFactory.get(extraInfo).deSerialize(payload, Request.class);
            log.info("decode =>requestId : {}, request:{}", messageId, JSONUtil.toJsonStr(request));
        }
        Header header = new Header(magicNum, Constants.VERSION_1, extraInfo, messageId, size);
        Message message = new Message<>(header, request);
        list.add(message);
    }
}

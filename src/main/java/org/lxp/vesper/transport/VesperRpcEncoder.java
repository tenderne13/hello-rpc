package org.lxp.vesper.transport;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.lxp.vesper.protocol.Header;
import org.lxp.vesper.protocol.Message;
import org.lxp.vesper.serialization.SerializationFactory;

public class VesperRpcEncoder extends MessageToByteEncoder<Message> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Message message, ByteBuf byteBuf) throws Exception {
        Header header = message.getHeader();
        byte extraInfo = header.getExtraInfo();
        Object content = message.getContent();
        byteBuf.writeShort(header.getMagic());
        byteBuf.writeByte(header.getVersion());
        byteBuf.writeByte(extraInfo);
        byteBuf.writeLong(header.getMessageId());

        byte[] serializeData = SerializationFactory.get(extraInfo).serialize(content);
        byteBuf.writeInt(serializeData.length);
        byteBuf.writeBytes(serializeData);
    }
}

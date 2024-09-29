package org.lxp.vesper.transport;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.lxp.vesper.protocol.Message;
import org.lxp.vesper.protocol.Response;

@Slf4j
public class VesperRpcClientHandler extends SimpleChannelInboundHandler<Message<Response>> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message<Response> responseMessage) throws Exception {
        Response content = responseMessage.getContent();
        log.info("client receive response:{}", content);
    }
}

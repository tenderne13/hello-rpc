package org.lxp.vesper.transport;

import cn.hutool.json.JSONUtil;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.lxp.vesper.protocol.Header;
import org.lxp.vesper.protocol.Message;
import org.lxp.vesper.protocol.Request;
import org.lxp.vesper.protocol.Response;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Slf4j
public class VesperRpcServerHandler extends SimpleChannelInboundHandler<Message<Request>> {

    static Executor executor = Executors.newCachedThreadPool();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message<Request> requestMessage) throws Exception {
        Header header = requestMessage.getHeader();
        Request content = requestMessage.getContent();
        log.info("receive request:{}", JSONUtil.toJsonStr(content));

        Response response = new Response();
        response.setCode(Response.OK);
        response.setResult("呦，是你小子");
        Message<Response> responseMessage = new Message<>(header, response);
        //直接回复
        ctx.writeAndFlush(responseMessage).addListener(ChannelFutureListener.CLOSE);
    }
}

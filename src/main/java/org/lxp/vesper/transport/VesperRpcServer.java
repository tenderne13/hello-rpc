package org.lxp.vesper.transport;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadFactory;

@Slf4j
public class VesperRpcServer {
    private EventLoopGroup boosGroup;
    private EventLoopGroup workerGroup;
    private ServerBootstrap serverBootstrap;
    protected int port;

    public VesperRpcServer(int port) {
        this.port = port;
        ThreadFactory bossThreadFactory = new DefaultThreadFactory("imBoss", true);
        boosGroup = new NioEventLoopGroup(1, bossThreadFactory);

        ThreadFactory workerThreadFactory = new DefaultThreadFactory("imWorker", true);
        workerGroup = new NioEventLoopGroup(4, workerThreadFactory);

        serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boosGroup, workerGroup).channel(NioServerSocketChannel.class).childOption(ChannelOption.TCP_NODELAY, Boolean.TRUE).childHandler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(new VesperRpcDecoder()).addLast(new VesperRpcEncoder()).addLast(new VesperRpcServerHandler());
            }
        });
    }

    public Channel start() throws InterruptedException {
        Channel channel = this.serverBootstrap.bind(this.port).sync().channel();
        log.info("vesper server started on port: {}", this.port);
        channel.closeFuture().addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                log.info("vesper server stopped on port: {}", port);
            }
        });
        return channel;
    }
}

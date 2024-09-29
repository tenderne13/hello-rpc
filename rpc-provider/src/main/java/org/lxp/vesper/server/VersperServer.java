package org.lxp.vesper.server;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.lxp.vesper.transport.VesperRpcServer;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class VersperServer implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            log.info("vesper server start");
            VesperRpcServer vesperRpcServer = new VesperRpcServer(2597);
            Channel channel = vesperRpcServer.start();
            channel.closeFuture().sync();
        } catch (Exception e) {
            log.error("vesper server start error", e);
        }
    }
}

package org.lxp.vesper;

import cn.hutool.json.JSONUtil;
import io.netty.channel.ChannelFuture;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.lxp.vesper.constants.Constants;
import org.lxp.vesper.protocol.Header;
import org.lxp.vesper.protocol.Message;
import org.lxp.vesper.protocol.Request;
import org.lxp.vesper.serialization.HessionSerialization;
import org.lxp.vesper.serialization.Serialization;
import org.lxp.vesper.serialization.SerializationFactory;
import org.lxp.vesper.transport.VesperRpcClient;
import org.lxp.vesper.transport.VesperRpcServer;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
@Slf4j
class SerializationTests {

    @Test
    void serializationTest() throws IOException {
        HessionSerialization hessionSerialization = new HessionSerialization();
        String str = "他让我喊他老刘";
        byte[] bytes = hessionSerialization.serialize(str);
        log.info("serialization result : {}", bytes);
        String deSerializeResult = hessionSerialization.deSerialize(bytes, String.class);
        log.info("deSerialize result : {}", deSerializeResult);
    }

    @Test
    void vesperRpcServerTest() throws InterruptedException {
        VesperRpcServer vesperRpcServer = new VesperRpcServer(2597);
        vesperRpcServer.start();
        Thread.sleep(100000000);
    }

    @Test
    void vesperRpcClientTest() throws InterruptedException, IOException {
        VesperRpcClient client = new VesperRpcClient("localhost", 2597);
        ChannelFuture connect = client.connect();
        Request request = new Request();
        request.setServiceName("org.lxp.vesper.service.UserService");
        request.setMethodName("getUser");
        request.setArgTypes(new Class[]{String.class});
        request.setArgs(new Object[]{"lxp"});
        Serialization serialization = SerializationFactory.get((byte) 1);
        int length = serialization.serialize(request).length;
        Header header = new Header(Constants.MAGIC_NUM, Constants.VERSION_1);
        header.setExtraInfo((byte) 1);
        header.setMessageId(System.currentTimeMillis());
        connect.channel().writeAndFlush(new Message<>(header, request));

        log.info("send request : {}", JSONUtil.toJsonStr(request));
        Thread.sleep(10000000);
    }
}

package org.lxp.vesper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.lxp.vesper.serialization.HessionSerialization;
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

}

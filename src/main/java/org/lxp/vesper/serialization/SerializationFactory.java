package org.lxp.vesper.serialization;

import java.util.HashMap;
import java.util.Map;

public class SerializationFactory {

    private static final Map<Byte, Serialization> serializationMap = new HashMap<>();

    static {
        serializationMap.put((byte) 1, new HessionSerialization());
        serializationMap.put((byte) 2, new ProtoStuffSerialization());
    }

    public static Serialization get(byte extraInfo) {
        return serializationMap.get((byte) 2);
    }
}

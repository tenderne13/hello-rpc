package org.lxp.vesper.serialization;

public class SerializationFactory {

    private static final HessionSerialization serialization = new HessionSerialization();

    public static Serialization get(byte type) {
        return serialization;
    }
}

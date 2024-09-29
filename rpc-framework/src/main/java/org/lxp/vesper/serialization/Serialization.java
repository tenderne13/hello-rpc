package org.lxp.vesper.serialization;

import java.io.IOException;

public interface Serialization {
    <T> byte[] serialize(T obj) throws IOException;

    <T> T deSerialize(byte[] data, Class<T> clz) throws IOException;
}

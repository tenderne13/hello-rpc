package org.lxp.vesper.serialization;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.io.IOException;

public class ProtoStuffSerialization implements Serialization {
    @Override
    public <T> byte[] serialize(T obj) throws IOException {
        if (obj == null) {
            return new byte[0];
        }
        Schema<T> schema = (Schema<T>) RuntimeSchema.getSchema(obj.getClass());
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        } finally {
            buffer.clear();
        }
    }

    @Override
    public <T> T deSerialize(byte[] data, Class<T> clz) throws IOException {
        if (data == null || data.length == 0) {
            return null;
        }
        Schema<T> schema = RuntimeSchema.getSchema(clz);
        T obj = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(data, obj, schema);
        return obj;
    }
}

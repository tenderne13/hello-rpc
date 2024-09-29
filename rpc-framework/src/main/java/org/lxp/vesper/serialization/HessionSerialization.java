package org.lxp.vesper.serialization;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.caucho.hessian.io.SerializerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class HessionSerialization implements Serialization {
    @Override
    public <T> byte[] serialize(T obj) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        HessianOutput hessianOutput = new HessianOutput(os);
        hessianOutput.writeObject(obj);
        return os.toByteArray();
    }

    @Override
    public <T> T deSerialize(byte[] data, Class<T> clz) throws IOException {
        InputStream is = new ByteArrayInputStream(data);
        HessianInput hessianInput = new HessianInput(is);
        return (T) hessianInput.readObject(clz);
    }
}

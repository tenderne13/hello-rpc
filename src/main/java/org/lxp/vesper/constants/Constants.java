package org.lxp.vesper.constants;


public class Constants {

    public static final short MAGIC_NUM = (short) 2597;

    public static final int HEADER_SIZE = 16;

    public static final byte VERSION_1 = 1;

    public static final int DEFAULT_TIMEOUT = 500000;

    public static final int CONNECTION_TIMEOUT = 2000;

    public static final int DEF_PORT = 9999;

    public static final int DEFAULT_IO_THREADS = Math.min(Runtime.getRuntime().availableProcessors() + 1, 32);

    public final static int HEARTBEAT_CODE = -1;

    public final static byte HEART_EXTRA_INFO = 1;

    public static boolean isHeartBeat(byte extraInfo) {
        return extraInfo == 0;
    }

    public static boolean isRequest(byte extraInfo) {
        return (extraInfo & 1) != 1;
    }

}

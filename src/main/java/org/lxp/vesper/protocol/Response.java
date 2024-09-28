package org.lxp.vesper.protocol;

import lombok.Data;

/**
 * rpc响应实体定义
 */
@Data
public class Response {
    /**
     * ok.
     */
    public static final byte OK = 20;

    /**
     * serialization error
     */
    public static final byte SERIALIZATION_ERROR = 25;

    /**
     * client side timeout.
     */
    public static final byte CLIENT_TIMEOUT = 30;

    /**
     * server side timeout.
     */
    public static final byte SERVER_TIMEOUT = 31;

    /**
     * channel inactive, directly return the unfinished requests.
     */
    public static final byte CHANNEL_INACTIVE = 35;

    /**
     * request format error.
     */
    public static final byte BAD_REQUEST = 40;

    /**
     * response format error.
     */
    public static final byte BAD_RESPONSE = 50;

    /**
     * service not found.
     */
    public static final byte SERVICE_NOT_FOUND = 60;

    /**
     * service error.
     */
    public static final byte SERVICE_ERROR = 70;

    /**
     * internal server error.
     */
    public static final byte SERVER_ERROR = 80;

    /**
     * internal server error.
     */
    public static final byte CLIENT_ERROR = 90;

    /**
     * server side threadpool exhausted and quick return.
     */
    public static final byte SERVER_THREADPOOL_EXHAUSTED_ERROR = 100;

    private int code = 0; // 响应的错误码，正常响应为0，非0表示异常响应

    private String errMsg; // 异常信息

    private Object result;
}

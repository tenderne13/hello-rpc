package org.lxp.vesper.protocol;

import lombok.Data;

import java.io.Serializable;

/**
 * rpc 请求体定义
 */
@Data
public class Request implements Serializable {
    private String serviceName;
    private String methodName;
    private Class[] argTypes;
    private Object[] args;
}

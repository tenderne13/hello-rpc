package org.lxp.vesper.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * rpc 请求体定义
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request implements Serializable {
    private String serviceName;
    private String methodName;
    private Class[] argTypes;
    private Object[] args;
}

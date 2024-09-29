package org.lxp.vesper.model.dto;

import lombok.Data;

@Data
public class UserQuery {
    private String userName;
    private Long tenantId;
}

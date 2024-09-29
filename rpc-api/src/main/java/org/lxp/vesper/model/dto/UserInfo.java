package org.lxp.vesper.model.dto;

import lombok.Data;

@Data
public class UserInfo {
    private String userName;
    private String password;
    private Long tenantId;
    private String email;
}

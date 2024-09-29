package org.lxp.vesper.user;

import org.lxp.vesper.model.dto.UserInfo;
import org.lxp.vesper.model.dto.UserQuery;

import java.util.List;

public interface UserService {
    List<UserInfo> queryUser(UserQuery userQuery);
}

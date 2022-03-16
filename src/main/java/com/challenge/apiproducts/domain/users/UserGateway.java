package com.challenge.apiproducts.domain.users;

import java.util.List;

public interface UserGateway {

    UserModel save(UserModel userModel);
    RoleModel saveRole(RoleModel roleModel);
    void addRoleToUser(String username, String roleName);
    UserModel findByUsername(String username);
    List<UserModel> findAll();
    UserModel update(long id, UserModel userModel);
}

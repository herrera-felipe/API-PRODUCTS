package com.challenge.apiproducts.domain.users;

import java.util.List;

public interface UserGateway {

    UserModel save(UserModel userModel);
    RoleModel saveRole(RoleModel roleModel);
    void addRoleToUser(String username, String roleName);
    UserModel findByUsername(String username);
    UserModel findByEmail(String email);
    List<UserModel> findAll();
    UserModel update(Long id, UserModel userModel);
}

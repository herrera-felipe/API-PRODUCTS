package com.challenge.apiproducts.domain.users;

import java.util.List;

public interface UserGateway {

    UserModel save(UserModel userModel);
    UserModel findByUsername(String username);
    UserModel findByEmail(String email);
    UserModel findById(Long id);
    List<UserModel> findAll();
    UserModel update(Long id, UserModel userModel);
}

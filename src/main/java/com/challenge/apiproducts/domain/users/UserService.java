package com.challenge.apiproducts.domain.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @Transactional
public class UserService {

    private final UserGateway userGateway;

    @Autowired
    public UserService(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public UserModel save(UserModel userModel) {
        return userGateway.save(userModel);
    }

    public UserModel update(Long id, UserModel userModel) {
        return userGateway.update(id, userModel);
    }

    public UserModel findById(Long id) {
        return userGateway.findById(id);
    }

    public UserModel findByUsername(String username) {
        return userGateway.findByUsername(username);
    }

    public UserModel findByEmail(String email) {
        return userGateway.findByEmail(email);
    }

    public List<UserModel> findAll() {
        return userGateway.findAll();
    }
}

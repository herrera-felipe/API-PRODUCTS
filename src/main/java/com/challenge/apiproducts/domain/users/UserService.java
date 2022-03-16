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

    public UserModel update(long id, UserModel userModel) {
        return userGateway.update(id, userModel);
    }

    public RoleModel saveRole(RoleModel roleModel) {
        return userGateway.saveRole(roleModel);
    }

    public void addRoleToUser(String username, String roleName) {
        userGateway.addRoleToUser(username, roleName);
    }

    public UserModel findByUsername(String username) {
        return userGateway.findByUsername(username);
    }

    public List<UserModel> findAll() {
        return userGateway.findAll();
    }
}

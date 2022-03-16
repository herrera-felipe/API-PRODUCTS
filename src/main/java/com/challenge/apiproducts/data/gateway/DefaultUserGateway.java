package com.challenge.apiproducts.data.gateway;

import com.challenge.apiproducts.data.entity.RoleEntity;
import com.challenge.apiproducts.data.entity.UserEntity;
import com.challenge.apiproducts.data.repository.RoleRepository;
import com.challenge.apiproducts.data.repository.UserRepository;
import com.challenge.apiproducts.domain.users.RoleModel;
import com.challenge.apiproducts.domain.users.UserGateway;
import com.challenge.apiproducts.domain.users.UserModel;
import com.challenge.apiproducts.web.exception.BadRequestException;
import com.challenge.apiproducts.web.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.*;

@Component
public class DefaultUserGateway implements UserGateway {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    @Autowired
    public DefaultUserGateway(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public UserModel save(UserModel userModel) {
        UserEntity entity = toEntity(userModel);
        entity.setCreated(LocalDateTime.now());
        return toModel(userRepository.save(entity));
    }


    @Override
    public RoleModel saveRole(RoleModel roleModel) {
        return toRoleModel(roleRepository.save(toRoleEntity(roleModel)));
    }


    @Override
    public void addRoleToUser(String username, String roleName) {
        UserEntity userEntity = userRepository.findByUsername(username);
        RoleEntity roleEntity = roleRepository.findByName(roleName);
        userEntity.getRole().add(roleEntity);
    }


    @Override
    public UserModel findByUsername(String username) {
        return toModel(userRepository.findByUsername(username));
    }


    @Override
    public List<UserModel> findAll() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream().map(this::toModel).collect(toList());
    }


    @Override
    public UserModel update(long id, UserModel userModel) {
        UserEntity entity = userRepository.findById(id).orElseThrow( () -> {
            return new ResourceNotFoundException("User not found");
        });

        if (!Objects.equals(entity.getEmail(), userModel.getEmail())
                && userRepository.findByEmail(userModel.getEmail()).isPresent()) {
            throw new BadRequestException("Email is already taken!");
        }

        UserEntity updatedEntity = updateUser(entity, userModel);
        return toModel(userRepository.save(updatedEntity));
    }


    private UserEntity toEntity(UserModel userModel) {
        return UserEntity.builder()
                .id(userModel.getId())
                .created(userModel.getCreated())
                .email(userModel.getEmail())
                .firstName(userModel.getFirstName())
                .lastName(userModel.getLastName())
                .password(userModel.getPassword())
                .updated(userModel.getUpdated())
                .username(userModel.getUsername())
                .role(userModel.getRole())
                .build();
    }


    private UserModel toModel(UserEntity entity) {
        return UserModel.builder()
                .id(entity.getId())
                .created(entity.getCreated())
                .email(entity.getEmail())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .password(entity.getPassword())
                .updated(entity.getUpdated())
                .role(entity.getRole())
                .build();
    }


    private UserEntity updateUser(UserEntity entity, UserModel model) {
        entity.setEmail(model.getEmail());
        entity.setFirstName(model.getFirstName());
        entity.setLastName(model.getLastName());
        entity.setPassword(model.getPassword());
        entity.setUpdated(LocalDateTime.now());
        entity.setRole(model.getRole());
        return entity;
    }


    private RoleEntity toRoleEntity(RoleModel role) {
        return RoleEntity.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }


    private RoleModel toRoleModel(RoleEntity entity) {
        return RoleModel.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}

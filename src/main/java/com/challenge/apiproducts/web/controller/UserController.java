package com.challenge.apiproducts.web.controller;

import com.challenge.apiproducts.domain.users.RoleModel;
import com.challenge.apiproducts.domain.users.UserModel;
import com.challenge.apiproducts.domain.users.UserService;
import com.challenge.apiproducts.web.dto.RoleDTO;
import com.challenge.apiproducts.web.dto.UserDTO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder encoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    @PostMapping("/save")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO dto) {
        dto.setPassword(encoder.encode(dto.getPassword()));
        UserModel userSaved = userService.save(toUserModel(dto));
        UserDTO userDTO = toUserDto(userSaved);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserModel> userModels = userService.findAll();
        List<UserDTO> dtoList = toUserDtoList(userModels);
        return ResponseEntity.ok().body(dtoList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable long id, @RequestBody UserDTO dto) {
        UserModel updatedUser = userService.update(id, toUserModel(dto));
        UserDTO userDTO = toUserDto(updatedUser);
        return ResponseEntity.ok().body(userDTO);
    }

    @PostMapping("/role")
    public ResponseEntity<RoleDTO> saveRole(@RequestBody RoleDTO dto) {
        RoleModel roleSaved = userService.saveRole(toRoleModel(dto));
        RoleDTO roleDTO = toRolDto(roleSaved);
        return ResponseEntity.status(HttpStatus.CREATED).body(roleDTO);
    }

    @PostMapping("/add/role")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }


    private UserDTO toUserDto(UserModel userModel) {
        return UserDTO.builder()
                .id(userModel.getId())
                .created(userModel.getCreated())
                .email(userModel.getEmail())
                .firstName(userModel.getFirstName())
                .lastName(userModel.getLastName())
                .password(userModel.getPassword())
                .updated(userModel.getUpdated())
                .username(userModel.getUsername())
                .roles(userModel.getRoles())
                .build();
    }


    private UserModel toUserModel(UserDTO userDTO) {
        return UserModel.builder()
                .id(userDTO.getId())
                .created(userDTO.getCreated())
                .email(userDTO.getEmail())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .password(userDTO.getPassword())
                .updated(userDTO.getUpdated())
                .username(userDTO.getUsername())
                .roles(userDTO.getRoles())
                .build();
    }


    private List<UserDTO> toUserDtoList(List<UserModel> users) {
        return users.stream().map(this::toUserDto).collect(toList());
    }


    private RoleModel toRoleModel(RoleDTO dto) {
        return RoleModel.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }


    private RoleDTO toRolDto(RoleModel model) {
        return RoleDTO.builder()
                .id(model.getId())
                .name(model.getName())
                .build();
    }
}

@Data
class RoleToUserForm {
    private String username;
    private String roleName;
}



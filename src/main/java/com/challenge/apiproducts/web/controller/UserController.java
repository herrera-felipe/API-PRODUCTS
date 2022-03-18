package com.challenge.apiproducts.web.controller;

import com.challenge.apiproducts.domain.users.UserModel;
import com.challenge.apiproducts.domain.users.UserService;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO dto) {
        UserModel updatedUser = userService.update(id, toUserModel(dto));
        UserDTO userDTO = toUserDto(updatedUser);
        return ResponseEntity.ok().body(userDTO);
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
                .role(userModel.getRole())
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
                .role(userDTO.getRole())
                .build();
    }


    private List<UserDTO> toUserDtoList(List<UserModel> users) {
        return users.stream().map(this::toUserDto).collect(toList());
    }

    @Data
    @Builder
    private static class UserDTO {
        private Long id;
        private LocalDateTime created;
        private String email;
        private String firstName;
        private String lastName;
        private String password;
        private LocalDateTime updated;
        private String username;
        private String role;
    }
}



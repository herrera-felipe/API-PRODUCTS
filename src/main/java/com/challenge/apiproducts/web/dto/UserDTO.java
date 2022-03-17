package com.challenge.apiproducts.web.dto;

import com.challenge.apiproducts.data.entity.RoleEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Collection;

@Data
@Builder
public class UserDTO {
    private Long id;
    private LocalDateTime created;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private LocalDateTime updated;
    private String username;
    private Collection<RoleEntity> roles;
}
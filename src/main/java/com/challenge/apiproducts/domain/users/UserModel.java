package com.challenge.apiproducts.domain.users;

import com.challenge.apiproducts.data.entity.RoleEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@Builder
public class UserModel {
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

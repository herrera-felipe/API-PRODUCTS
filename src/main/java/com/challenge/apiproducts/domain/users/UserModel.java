package com.challenge.apiproducts.domain.users;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
    private String role;
}

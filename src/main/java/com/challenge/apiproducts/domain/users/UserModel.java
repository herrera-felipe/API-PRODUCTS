package com.challenge.apiproducts.domain.users;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class UserModel {
    private Long id;
    private LocalDate created;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private LocalDate updated;
    private String username;
    private String role;
}

package com.challenge.apiproducts.domain.users;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RoleModel {
    private Long id;
    private String name;
}

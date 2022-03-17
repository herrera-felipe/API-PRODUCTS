package com.challenge.apiproducts.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDTO {
    private long id;
    private String name;
}

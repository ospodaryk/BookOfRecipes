package com.project.recipes.dto.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private String id;
    private String firstName;
    private String email;
    private String password;
    //private String role;
}

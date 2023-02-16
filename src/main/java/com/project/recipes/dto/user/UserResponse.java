package com.project.recipes.dto.user;

import com.project.recipes.model.Recipe;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class UserResponse {
    private String id;
    private String firstName;
    private String email;
    private String role;
}

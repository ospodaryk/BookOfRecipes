package com.project.recipes.dto.user;


import lombok.Data;

import javax.validation.constraints.Pattern;

@Data

public class UserRequestToChangeRole {
    private String role;
}

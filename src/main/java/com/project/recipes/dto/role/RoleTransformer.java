package com.project.recipes.dto.role;

import com.project.recipes.dto.user.UserTransformer;
import com.project.recipes.model.Recipe;
import com.project.recipes.model.Role;
import com.project.recipes.service.UserService;
import org.modelmapper.ModelMapper;

public class RoleTransformer {
    private final ModelMapper modelMapper;

    public RoleTransformer(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Role convertRoleRequestToRole(RoleRequest roleRequest) {
        return modelMapper.map(roleRequest, Role.class);
    }
}

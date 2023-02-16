package com.project.recipes.dto.role;

import com.project.recipes.model.Role;
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

package com.project.recipes.dto.role;

import com.project.recipes.model.Role;
import com.project.recipes.service.implementation.RoleServiceImpl;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RoleTransformer {
    private final ModelMapper modelMapper;
    private static final Logger logger = LoggerFactory.getLogger(RoleTransformer.class);

    public RoleTransformer(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Role convertRoleRequestToRole(RoleRequest roleRequest) {
        logger.info("convertRoleRequestToRole");
        return modelMapper.map(roleRequest, Role.class);
    }
}

package com.project.recipes.dto.user;

import com.project.recipes.model.User;
import com.project.recipes.service.RoleService;
import org.modelmapper.ModelMapper;


public class UserTransformer {
    private final ModelMapper modelMapper;
    private final RoleService roleService;


    public UserTransformer(ModelMapper modelMapper, RoleService roleService) {
        this.modelMapper = modelMapper;
        this.roleService = roleService;
    }

    public User convertUserRequestToUser(UserRequest userRequest) {
        return modelMapper.map(userRequest, User.class);
    }

    public UserResponse convertToUserResponse(User user) {
        UserResponse userResponse = modelMapper.map(user, UserResponse.class);
        //userResponse.setRole(user.getRole().getName());
        return userResponse;
    }


}

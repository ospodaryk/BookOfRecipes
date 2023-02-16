package com.project.recipes.dto.user;

import com.project.recipes.model.User;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UserTransformer {
    private final ModelMapper modelMapper;
    private static final Logger logger = LoggerFactory.getLogger(UserTransformer.class);


    public UserTransformer(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public User convertUserRequestToUser(UserRequest userRequest) {
        logger.info("convertUserRequestToUser");
        return modelMapper.map(userRequest, User.class);
    }

    public UserResponse convertToUserResponse(User user) {
        UserResponse userResponse = modelMapper.map(user, UserResponse.class);
        userResponse.setRole(user.getRole().getName());
        logger.info("convertToUserResponse");
        return userResponse;
    }
}

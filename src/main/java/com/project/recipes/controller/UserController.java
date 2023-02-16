package com.project.recipes.controller;

import com.project.recipes.dto.user.UserRequest;
import com.project.recipes.dto.user.UserResponse;
import com.project.recipes.dto.user.UserTransformer;
import com.project.recipes.model.User;
import com.project.recipes.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.project.recipes.exception.FieldsValidationErrors.returnErrorsToClient;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final UserTransformer userTransformer;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService, UserTransformer userTransformer) {
        this.userService = userService;
        this.userTransformer = userTransformer;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    List<UserResponse> getAllUsers() {
        logger.info("@Get: getAllUsers()");
        return userService.getAll().stream()
                .map(userTransformer::convertToUserResponse)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('ADMIN') or #id==authentication.principal.id")
    @GetMapping("/{id}")
    UserResponse getUser(@PathVariable long id) {
        logger.info("@Get: getUser(), id=" + id);
        return userTransformer.convertToUserResponse(userService.readById(id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable long id) {
        userService.delete(id);
        logger.info("@Delete: deleteUser(), id=" + id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse createUser(@RequestBody @Valid UserRequest userRequest,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("@Post: create() has errors " + bindingResult.getAllErrors().toString());
            returnErrorsToClient(bindingResult);
        }

        UserResponse userResponse = userTransformer.convertToUserResponse(userService.create(userTransformer.convertUserRequestToUser(userRequest)));
        logger.info("@Post: create(), id=" + userTransformer.convertUserRequestToUser(userRequest).getId());
        return userResponse;
    }

    @PreAuthorize("hasAuthority('ADMIN') or #id==authentication.principal.id")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> updateUser(@PathVariable Long id, @RequestBody @Valid UserRequest userRequest,
                                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("@Post: update() has errors " + bindingResult.getAllErrors().toString());
            returnErrorsToClient(bindingResult);
        }
        User newUser = userTransformer.convertUserRequestToUser(userRequest);
        userService.update(newUser, id);
        logger.info("@Post: update(), id=" + userTransformer.convertUserRequestToUser(userRequest).getId());
        return ResponseEntity.ok(HttpStatus.OK);
    }

}

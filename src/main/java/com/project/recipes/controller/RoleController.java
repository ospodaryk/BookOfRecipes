package com.project.recipes.controller;

import com.project.recipes.dto.user.UserRequest;
import com.project.recipes.dto.user.UserResponse;
import com.project.recipes.dto.user.UserTransformer;
import com.project.recipes.model.Role;
import com.project.recipes.model.User;
import com.project.recipes.service.RoleService;
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

@PreAuthorize("hasAuthority('ADMIN')")
@RestController
@RequestMapping("/api/role")
public class RoleController {
    private final RoleService roleService;
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    List<Role> getAll() {
        logger.info("@Get: getAllUsers()");
        return roleService.getAll();
    }

    @GetMapping("/{id}")
    Role getUser(@PathVariable long id) {
        logger.info("@Get: getUser(), id=" + id);
        return roleService.readById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable long id) {
        roleService.delete(id);
        logger.info("@Delete: deleteUser(), id=" + id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> createUser(@RequestBody @Valid Role role,
                                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("@Post: create() has errors " + bindingResult.getAllErrors().toString());
            returnErrorsToClient(bindingResult);
        }

        roleService.create(role);
        logger.info("@Post: create(), id=" + roleService.create(role));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> updateUser(@PathVariable Long id, @RequestBody @Valid Role role,
                                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("@Post: update() has errors " + bindingResult.getAllErrors().toString());
            returnErrorsToClient(bindingResult);
        }
        role.setId(id);
        roleService.update(role);
        logger.info("@Post: update(), id=" +  roleService.update(role).getId());
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

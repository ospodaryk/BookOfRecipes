package com.project.recipes.controller;

import com.project.recipes.dto.role.RoleRequest;
import com.project.recipes.dto.role.RoleTransformer;
import com.project.recipes.model.Role;
import com.project.recipes.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.project.recipes.exception.FieldsValidationErrors.returnErrorsToClient;

@PreAuthorize("hasAuthority('ADMIN')")
@RestController
@RequestMapping("/api/role")
public class RoleController {
    private final RoleService roleService;
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
    private final RoleTransformer roleTransformer;

    @Autowired
    public RoleController(RoleService roleService, RoleTransformer roleTransformer) {
        this.roleService = roleService;
        this.roleTransformer = roleTransformer;
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    List<Role> getAll() {
        logger.info("@Get: getAllUsers()");
        return roleService.getAll();
    }

    @GetMapping("/{id}")
    Role getRole(@PathVariable long id) {
        logger.info("@Get: getUser(), id=" + id);
        return roleService.readById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRole(@PathVariable long id) {
        roleService.delete(id);
        logger.info("@Delete: deleteUser(), id=" + id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> createRole(@RequestBody @Valid RoleRequest roleRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("@Post: create() has errors " + bindingResult.getAllErrors().toString());
            returnErrorsToClient(bindingResult);
        }

        Role role = roleService.create(roleTransformer.convertRoleRequestToRole(roleRequest));
        logger.info("@Post: create(), id=" + role);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}

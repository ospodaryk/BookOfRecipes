package com.project.recipes.service;


import com.project.recipes.model.Role;

import java.util.List;

public interface RoleService {
    Role create(Role role);

    Role readById(long id);

    Role update(Role role);

    void delete(long id);

    List<Role> getAll();

    Role readByName(String name);
}

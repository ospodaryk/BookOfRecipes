package com.project.recipes.service;

import com.project.recipes.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User create(User user);

    User readById(long id);

    User update(User user, Long id);

    void delete(long id);

    List<User> getAll();
}

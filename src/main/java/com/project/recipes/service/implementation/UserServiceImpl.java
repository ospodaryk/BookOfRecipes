package com.project.recipes.service.implementation;

import com.project.recipes.exception.NullEntityReferenceException;
import com.project.recipes.model.User;
import com.project.recipes.repository.UserRepository;
import com.project.recipes.service.RoleService;
import com.project.recipes.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
    private final RoleService roleRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public User create(User user) {
        if (user != null) {
            if (user.getRole() == null) {
                user.setRole(roleRepository.readById(1L));
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            logger.info("User create success");
            return userRepository.save(user);
        }
        logger.error("User cannot 'null'");
        throw new NullEntityReferenceException("User cannot be 'null'");
    }

    @Override
    public User readById(long id) {
        logger.info("get ById id=" + id);
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    }

    @Override
    public User update(User user, Long id) {
        if (user != null && id > 0) {
            user.setId(readById(id).getId());
            if (user.getRole() == null) {
                user.setRole(readById(id).getRole());
            }
            logger.info("Updated User " + user);
            return userRepository.save(user);
        }
        logger.error("User to update cannot be 'null'");
        throw new NullEntityReferenceException("User cannot be 'null'");
    }

    @Override
    public void delete(long id) {
        User user = readById(id);
        logger.info("Delete user by ID=" + id);
        userRepository.delete(user);
    }

    @Override
    public List<User> getAll() {
        logger.info("Get all Users");
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("loadUserByUsername username:" + username);
        User user = userRepository.findByEmail(username);
        if (user == null) {
            logger.error("User not Found!");
            throw new UsernameNotFoundException("User not Found!");
        }
        return user;
    }
}

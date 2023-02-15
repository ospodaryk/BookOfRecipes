//package com.project.recipes.controller;
//
//import com.project.recipes.model.User;
//import com.project.recipes.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.server.ResponseStatusException;
//
//import javax.sql.DataSource;
//
//@RestController
//public class RegistrationController {
//
//    @Autowired
//    UserRepository userRepo;
//
//    @Autowired
//    PasswordEncoder encoder;
//
//    @Autowired
//    DataSource dataSource;
//
//    @PostMapping("/api/register")
//    public void register(@RequestBody User user) {
//        boolean isUser = userRepo.findUserByEmail(user.getEmail()) == null;
//
//        if (isUser && user.getEmail().trim().matches(".+@{1}.+\\..+") && user.getPassword().trim().matches(".{8,}")) {
//            user.setPassword(encoder.encode(user.getPassword()));
//            user.setRolesAndAuthorities("USER");
//            user.setEnabled(true);
//            userRepo.save(user);
//        } else {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//        }
//    }
//
//}

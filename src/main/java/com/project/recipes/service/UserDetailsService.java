//package com.project.recipes.service;
//
//import com.project.recipes.model.User;
//import com.project.recipes.repository.UserRepository;
//import com.project.recipes.service.implementation.UserDetailsImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
//
//    @Autowired
//    UserRepository userRepo;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepo.findUserByEmail(username);
//
//        if (user == null) {
//            throw new UsernameNotFoundException("Not found: " + username);
//        } else {
//            return new UserDetailsImpl(user);
//        }
//    }
//
//}

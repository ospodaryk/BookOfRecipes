package com.project.recipes.config;

import com.project.recipes.model.Recipe;
import com.project.recipes.model.User;
import com.project.recipes.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service("securityService")
public class PersonSecurityService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(PersonSecurityService.class);

    private final UserRepository userRepository;
    private Authentication authentication;

    @Autowired
    public PersonSecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with this email does not exists");
        }
        return new PersonSecurity(user);
    }

    public boolean accessToTasks(long todoId) {
        this.authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailUser = this.authentication.getName();
        PersonSecurity user = (PersonSecurity) loadUserByUsername(emailUser);
        List<Long> todoIds = (user.getMyTodos().stream())
                .map(Recipe::getId)
                .toList();
        return todoIds.contains(todoId) || user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList().contains("ADMIN");
    }
}

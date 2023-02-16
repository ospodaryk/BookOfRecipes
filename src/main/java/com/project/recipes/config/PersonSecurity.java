package com.project.recipes.config;


import com.project.recipes.model.Recipe;
import com.project.recipes.model.User;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Data
public class PersonSecurity implements UserDetails {
    private static final Logger logger = LoggerFactory.getLogger(PersonSecurity.class);

    private long id;
    private String firstName;
    private String username;
    private String password;
    private List<GrantedAuthority> authorities;
    private List<Recipe> recipes;



    public PersonSecurity(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.username = user.getEmail();
        this.password = user.getPassword();
        this.authorities = List.of(new SimpleGrantedAuthority(user.getRole().getName()));
        this.recipes = user.getRecipes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {
        return true;
    }
}


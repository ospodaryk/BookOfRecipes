//package com.project.recipes.model;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.*;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotEmpty;
//import lombok.Data;
//@Data
//@Entity(name = "User")
//@Table(name = "USERS")
//@SecondaryTable(name = "AUTHORITIES", pkJoinColumns = @PrimaryKeyJoinColumn(name = "username"))
//public class User {
//
//    @Id
//    @NotBlank
//    @Column(name = "username")
//    private String email;
//
//    @Column(name = "password")
//    @NotBlank
//    private String password;
//
//    @NotNull
//    @Column(name = "enabled")
//    private boolean isEnabled;
//
//    @NotEmpty
//    @Column(name = "authority", table = "AUTHORITIES")
//    private String rolesAndAuthorities;
//
//    public User(String email, String password, boolean isEnabled, String authority) {
//        this.email = email;
//        this.password = password;
//        this.isEnabled = isEnabled;
//        this.rolesAndAuthorities = authority;
//    }
//
//    public User(String email, String password) {
//        this.email = email;
//        this.password = password;
//        this.isEnabled = true;
//        this.rolesAndAuthorities = "USER";
//    }
//
//    public User() {
//    }
//
//}

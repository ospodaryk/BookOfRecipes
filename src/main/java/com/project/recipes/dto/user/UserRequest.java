package com.project.recipes.dto.user;


import lombok.Data;

import javax.validation.constraints.Pattern;

//@Value
//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserRequest {
//    @Pattern(regexp = "[A-Z][a-z]+",
//            message = "Must start with a capital letter followed by one or more lowercase letters")
    private String firstName;

//    @Pattern(regexp = "[A-Z][a-z]+",
//            message = "Must start with a capital letter followed by one or more lowercase letters")
    private String lastName;

//    @Pattern(regexp = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}", message = "Must be a valid e-mail address")
    private String email;

//    @Pattern(regexp = "[A-Za-z\\d]{6,}",
//            message = "Must be minimum 6 symbols long, using digits and latin letters")
//    @Pattern(regexp = ".*\\d.*",
//            message = "Must contain at least one digit")
//    @Pattern(regexp = ".*[A-Z].*",
//            message = "Must contain at least one uppercase letter")
//    @Pattern(regexp = ".*[a-z].*",
//            message = "Must contain at least one lowercase letter")
    private String password;

    //private String role;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
}

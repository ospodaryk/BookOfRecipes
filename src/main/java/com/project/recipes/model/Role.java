//package com.project.recipes.model;
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//import org.hibernate.Hibernate;
////import org.hibernate.annotations.Table;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//
////import javax.persistence.*;
////import javax.validation.constraints.NotBlank;
//import java.util.List;
//import java.util.Objects;
//
//@Getter @Setter @NoArgsConstructor @ToString
//@Entity
//@Table(name = "roles")
//public class Role implements GrantedAuthority {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @NotBlank(message = "The 'name' cannot be empty")
//    @Column(name = "name", nullable = false, unique = true)
//    private String name;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        Role role = (Role) o;
//        return getId() != null && getId().equals(role.getId());
//    }
//
//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }
//
//    @Override
//    public String getAuthority() {
//        return "ROLE_" + name;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//}

package com.project.recipes;

import com.project.recipes.dto.recipe.RecipeTransformer;
import com.project.recipes.dto.role.RoleTransformer;
import com.project.recipes.dto.user.UserTransformer;
import com.project.recipes.service.RoleService;
import com.project.recipes.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RecipesApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecipesApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public UserTransformer userTransformer(ModelMapper modelMapper, RoleService roleService) {
        return new UserTransformer(modelMapper, roleService);
    }

    @Bean
    public RecipeTransformer recipeTransformer(ModelMapper modelMapper) {
        return new RecipeTransformer(modelMapper);
    }

    @Bean
    public RoleTransformer roleTransformer(ModelMapper modelMapper) {
        return new RoleTransformer(modelMapper);
    }
}

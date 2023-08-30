package com.project.recipes;

import com.project.recipes.dto.recipe.RecipeTransformer;
import com.project.recipes.dto.role.RoleTransformer;
import com.project.recipes.dto.user.UserTransformer;
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
    public UserTransformer userTransformer(ModelMapper modelMapper) {
        String s1="hello world1";
        String s2="hello world1";
        if(s1==s2){
            System.out.println("ITS EQUALS_WEEE");
        }
        return new UserTransformer(modelMapper);
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

package com.project.recipes;

import com.project.recipes.dto.RecipeTransformer;
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
    public RecipeTransformer recipeTransformer(ModelMapper modelMapper) {
        return new RecipeTransformer(modelMapper);
    }
}

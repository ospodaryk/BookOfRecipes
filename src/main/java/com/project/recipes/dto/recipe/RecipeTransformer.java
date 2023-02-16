package com.project.recipes.dto.recipe;

import com.project.recipes.dto.user.UserTransformer;
import com.project.recipes.model.Recipe;
import com.project.recipes.service.RoleService;
import com.project.recipes.service.UserService;
import lombok.Data;
import org.modelmapper.ModelMapper;
public class RecipeTransformer {
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final UserTransformer userTransformer;

    public RecipeTransformer(ModelMapper modelMapper, UserService userService, UserTransformer userTransformer) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.userTransformer = userTransformer;
    }

    public Recipe convertRecipeRequestToRecipe(RecipeRequest recipeRequest) {
        Recipe recipe = new Recipe();

        recipe.setName(recipeRequest.getName());
        recipe.setCategory(recipeRequest.getCategory());
        return modelMapper.map(recipeRequest, Recipe.class);
    }


    public RecipeResponse convertToRecipeResponse(Recipe recipe) {
        RecipeResponse recipeResponse = modelMapper.map(recipe, RecipeResponse.class);
        recipeResponse.setId(recipe.getId());
        return recipeResponse;
    }
    public RecipeResponseForAdmin convertToRecipeResponseForAdmin(Recipe recipe) {
        RecipeResponseForAdmin recipeResponse = modelMapper.map(recipe, RecipeResponseForAdmin.class);
        recipeResponse.setId(recipe.getId());
        recipeResponse.setOwner_id((recipe.getOwner().getId()));
        return recipeResponse;
    }

}

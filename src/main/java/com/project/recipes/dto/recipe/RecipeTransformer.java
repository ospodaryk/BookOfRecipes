package com.project.recipes.dto.recipe;

import com.project.recipes.model.Recipe;
import org.modelmapper.ModelMapper;

public class RecipeTransformer {
    private final ModelMapper modelMapper;

    public RecipeTransformer(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
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

}

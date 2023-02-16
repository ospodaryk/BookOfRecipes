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
        return recipeResponse;
    }

    public RecipeResponseForAdmin convertToRecipeResponseForAdmin(Recipe recipe) {
        RecipeResponseForAdmin recipeResponse = modelMapper.map(recipe, RecipeResponseForAdmin.class);
        recipeResponse.setId(recipe.getId());
        recipeResponse.setOwner_id((recipe.getOwner().getId()));
        return recipeResponse;
    }

}

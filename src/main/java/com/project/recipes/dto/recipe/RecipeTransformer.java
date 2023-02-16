package com.project.recipes.dto.recipe;


import com.project.recipes.model.Recipe;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecipeTransformer {
    private final ModelMapper modelMapper;
    private static final Logger logger = LoggerFactory.getLogger(RecipeTransformer.class);

    public RecipeTransformer(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Recipe convertRecipeRequestToRecipe(RecipeRequest recipeRequest) {
        logger.info("convertRecipeRequestToRecipe");
        Recipe recipe = new Recipe();
        recipe.setName(recipeRequest.getName());
        recipe.setCategory(recipeRequest.getCategory());
        return modelMapper.map(recipeRequest, Recipe.class);
    }


    public RecipeResponse convertToRecipeResponse(Recipe recipe) {
        logger.info("convertToRecipeResponse");
        RecipeResponse recipeResponse = modelMapper.map(recipe, RecipeResponse.class);
        return recipeResponse;
    }

    public RecipeResponseForAdmin convertToRecipeResponseForAdmin(Recipe recipe) {
        logger.info("convertToRecipeResponseForAdmin");
        RecipeResponseForAdmin recipeResponse = modelMapper.map(recipe, RecipeResponseForAdmin.class);
        recipeResponse.setId(recipe.getId());
        recipeResponse.setOwner_id((recipe.getOwner().getId()));
        return recipeResponse;
    }

}

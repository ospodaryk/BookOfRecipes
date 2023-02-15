package com.project.recipes.controller;

import com.project.recipes.dto.recipe.RecipeRequest;
import com.project.recipes.dto.recipe.RecipeResponse;
import com.project.recipes.dto.recipe.RecipeTransformer;
import com.project.recipes.model.Recipe;
import com.project.recipes.service.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/recipe")
public class RecipeController {
    private static final Logger logger = LoggerFactory.getLogger(RecipeController.class);
    private RecipeService recipeService;
    private RecipeTransformer recipeTransformer;

    @Autowired
    public RecipeController(RecipeService recipeService, RecipeTransformer recipeTransformer) {
        this.recipeService = recipeService;
        this.recipeTransformer = recipeTransformer;
    }

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping()
    public List<Recipe> getAllRecipes() {
        return recipeService.getAll();
    }


    @PostMapping("/new")
    public Recipe createRecipe(@RequestBody RecipeRequest newRecipe) {
        return recipeService.create(recipeTransformer.convertRecipeRequestToRecipe(newRecipe));
    }


    @GetMapping("/{id}")
    public RecipeResponse getRecipe(@PathVariable("id") long id) {
        logger.info("@Get: getRecipe by id()");
        return recipeTransformer.convertToRecipeResponse(recipeService.readById(id));
    }
//    @GetMapping("/all/users/{user_id}")
//    public  List<RecipeResponse> getALLRecipesByUser(@PathVariable("user_id") long userId) {
//        logger.info("@Get: getALLRecipesByUser(), id="+userId);
//        return recipeService
//                .getByUserId(userId)
//                .stream()
//                .map(toDoTransformer::convertToToDoResponse)
//                .collect(Collectors.toList());
//    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<HttpStatus> deleteRecipe(@PathVariable("id") long id) {
        recipeService.delete(id);
        logger.info("@Delete: deleteRecipe(), id=" + id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteALLRecipe() {
        recipeService.deleteAll();
        logger.info("@Delete: deleteALLRecipe()");
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<HttpStatus> update(@RequestBody RecipeRequest recipeRequest,
                                             @PathVariable("id") long id) {
        Recipe recipe = recipeTransformer.convertRecipeRequestToRecipe(recipeRequest);
        recipe.setId(id);
        recipeService.update(recipe);
        logger.info("@Post: update(), id=" + recipe.getId());
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

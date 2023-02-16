package com.project.recipes.controller;

import com.project.recipes.dto.recipe.RecipeRequest;
import com.project.recipes.dto.recipe.RecipeResponse;
import com.project.recipes.dto.recipe.RecipeResponseForAdmin;
import com.project.recipes.dto.recipe.RecipeTransformer;
import com.project.recipes.model.Recipe;
import com.project.recipes.service.RecipeService;
import com.project.recipes.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    private static final Logger logger = LoggerFactory.getLogger(RecipeController.class);
    private RecipeService recipeService;
    private RecipeTransformer recipeTransformer;
    private UserService userService;

    @Autowired
    public RecipeController(RecipeService recipeService, RecipeTransformer recipeTransformer, UserService userService) {
        this.recipeService = recipeService;
        this.recipeTransformer = recipeTransformer;
        this.userService = userService;
    }


    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PreAuthorize("hasAuthority('ADMIN') or #owner_id==authentication.principal.id")
    @GetMapping("/all/users/{owner_id}")
    public List<RecipeResponse> getAllRecipesByUser(@PathVariable("owner_id") long owner_id) {
        return recipeService.getByUserId(owner_id)
                .stream()
                .map(recipeTransformer::convertToRecipeResponse)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping()
    public List<RecipeResponseForAdmin> getAllRecipes() {
        return recipeService.getAll().stream()
                .map(recipeTransformer::convertToRecipeResponseForAdmin)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('ADMIN') or #owner_id==authentication.principal.id")
    @PostMapping("/create/users/{owner_id}")
    public List<RecipeResponse> createRecipe(@PathVariable("owner_id") long owner_id, @RequestBody RecipeRequest newRecipe) {
        logger.info("_____createRecipe");
        Recipe recipe = recipeTransformer.convertRecipeRequestToRecipe(newRecipe);
        recipe.setOwner(userService.readById(owner_id));
        logger.info("===" + recipe.toString());
        recipeService.create(recipe);
        return recipeService.getAll().stream().map(recipeTransformer::convertToRecipeResponse).collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('ADMIN') or authentication.principal.id==@recipeServiceImpl.readById(#id).owner.id")
    @GetMapping("/{id}/read")
    public RecipeResponse getRecipe(@PathVariable("id") long id) {
        logger.info("@Get: getRecipe by id()");
        return recipeTransformer.convertToRecipeResponse(recipeService.readById(id));
    }


    @PreAuthorize("hasAuthority('ADMIN') or authentication.principal.id==@recipeServiceImpl.readById(#id).owner.id")
    @DeleteMapping("/{id}/remove")
    public ResponseEntity<HttpStatus> deleteRecipe(@PathVariable("id") long id) {
        recipeService.delete(id);
        logger.info("@Delete: deleteRecipe(), id=" + id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/remove")
    public ResponseEntity<HttpStatus> deleteALLRecipe() {
        recipeService.deleteAll();
        logger.info("@Delete: deleteALLRecipe()");
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN') or authentication.principal.id==@recipeServiceImpl.readById(#id).owner.id")
    @PutMapping("/{id}/update")
    public ResponseEntity<HttpStatus> update(@RequestBody RecipeRequest recipeRequest, @PathVariable("id") long id) {
        Recipe recipe = recipeTransformer.convertRecipeRequestToRecipe(recipeRequest);
        recipe.setId(id);
        recipeService.update(recipe);
        logger.info("@Post: update(), id=" + recipe.getId());
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

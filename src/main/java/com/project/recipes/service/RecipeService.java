package com.project.recipes.service;



import com.project.recipes.model.Recipe;

import java.util.List;

public interface RecipeService {
    void deleteAll();

    Recipe create(Recipe todo);

    Recipe readById(long id);

    Recipe update(Recipe todo);

    void delete(long id);

    List<Recipe> getAll();

}

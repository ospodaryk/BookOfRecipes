package com.project.recipes.service.implementation;


import com.project.recipes.model.Recipe;
import com.project.recipes.repository.RecipeRepository;
import com.project.recipes.service.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImplementsation implements RecipeService {
    private final RecipeRepository recipeRepository;
//    public static int count = 1;

    private static final Logger logger = LoggerFactory.getLogger(RecipeServiceImplementsation.class);

    public RecipeServiceImplementsation(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Recipe create(Recipe recipe) {
        if (recipe != null) {
            logger.info("_________________Recipe create");
//            recipe.setId(count);
//            count++;
            return recipeRepository.save(recipe);
        }
        logger.error("_________________Recipe null");

        System.out.println("OOOPS");
        return null;
    }

    @Override
    public void deleteAll() {
        recipeRepository.findAll().forEach(obj -> delete(obj.getId()));
    }

    @Override
    public Recipe readById(long id) {
        var x = recipeRepository.findById(id);
        return x.get();
    }

    @Override
    public Recipe update(Recipe todo) {
        if (todo != null) {
            readById(todo.getId());
            return recipeRepository.save(todo);
        }
        return null;
    }

    @Override
    public void delete(long id) {

        Recipe todo = readById(id);
        recipeRepository.delete(todo);
//        count--;
    }

    @Override
    public List<Recipe> getAll() {
        return recipeRepository.findAll();
    }

}

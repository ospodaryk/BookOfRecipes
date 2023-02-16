package com.project.recipes.service.implementation;


import com.project.recipes.exception.NullEntityReferenceException;
import com.project.recipes.model.Recipe;
import com.project.recipes.repository.RecipeRepository;
import com.project.recipes.service.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;

    private static final Logger logger = LoggerFactory.getLogger(RecipeServiceImpl.class);

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Recipe create(Recipe recipe) {
        if (recipe != null) {
            logger.info("Recipe create success");
            return recipeRepository.save(recipe);
        }
        logger.error("Recipe  cannot 'null'");
        throw new NullEntityReferenceException("Recipe cannot be 'null'");
    }

    @Override
    public List<Recipe> getByUserId(long userId) {
        logger.info("get ByUserId id=" + userId);
        return recipeRepository.findAll()
                .stream()
                .filter(e -> (e.getOwner().getId().equals(userId)))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        logger.info("Delete all recipes");
        recipeRepository.findAll().forEach(obj -> delete(obj.getId()));
    }

    @Override
    public Recipe readById(long id) {
        logger.info("Read recipe by ID="+id);
        return recipeRepository.findById(id).orElseThrow(() -> {
            logger.error("Recipe with id " + id + " not found");
            throw new EntityNotFoundException("Recipe with id " + id + " not found");
        });
    }

    @Override
    public Recipe update(Recipe recipe) {
        if (recipe != null) {
            readById(recipe.getId());
            logger.info("Updated recipe "+recipe);
            return recipeRepository.save(recipe);
        }
        logger.error("Recipe to update cannot be 'null'");
        throw new NullEntityReferenceException("Recipe to update cannot be 'null'");
    }

    @Override
    public void delete(long id) {
        Recipe recipe = readById(id);
        logger.info("Delete recipe by ID="+id);
        recipeRepository.delete(recipe);
    }

    @Override
    public List<Recipe> getAll() {
        logger.info("Get all Recipes");
        return recipeRepository.findAll();
    }

}

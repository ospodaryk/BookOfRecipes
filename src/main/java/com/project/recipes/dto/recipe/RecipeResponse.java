package com.project.recipes.dto.recipe;

import com.project.recipes.dto.user.UserResponse;
import com.project.recipes.model.User;
import lombok.Data;

import java.util.ArrayList;

@Data
public class RecipeResponse {
    private String name;
    private String description;
    private String category;
    private ArrayList<String> ingredients;
    private ArrayList<String> directions;
}

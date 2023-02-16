package com.project.recipes.dto.recipe;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
public class RecipeResponse {
    private String name;
    private String description;
    private String category;
    private ArrayList<String> ingredients;
    private ArrayList<String> directions;
    private LocalDateTime date;
}

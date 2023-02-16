package com.project.recipes.dto.recipe;

import lombok.Data;

import java.util.ArrayList;

@Data
public class RecipeResponseForAdmin {
    private Long id;
    private String name;
    private String description;
    private String category;
    private ArrayList<String> ingredients;
    private ArrayList<String> directions;
    private long owner_id;
}

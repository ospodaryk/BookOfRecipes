package com.project.recipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Recipe")
@Table(name = "RECIPES")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id = 0L;

    //    @NotBlank
    @Column(name = "name")
    private String name;

    //    @NotBlank
    @Column(name = "category")
    private String category;


    @Column(name = "date")
    private LocalDateTime date = LocalDateTime.now();

    //    @NotBlank
    @Column(name = "description")
    private String description;

    //    @NotEmpty
    @ElementCollection
    @CollectionTable(name = "INGREDIENTS", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "ingredients")
    private List<String> ingredients = new ArrayList<>();

    //    @NotEmpty
    @ElementCollection
    @CollectionTable(name = "DIRECTIONS", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "directions")
    private List<String> directions = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "owner")
    private User owner;

}

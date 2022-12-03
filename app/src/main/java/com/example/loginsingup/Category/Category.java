package com.example.loginsingup.Category;

import com.example.loginsingup.model;

import java.util.List;

public class Category {
    private String nameCategory;
    private List<model> models;

    public Category(String nameCategory, List<model> models) {
        this.nameCategory = nameCategory;
        this.models = models;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public List<model> getModels() {
        return models;
    }

    public void setModels(List<model> models) {
        this.models = models;
    }
}

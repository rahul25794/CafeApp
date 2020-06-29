package com.cafe.app.objects;

import java.util.List;

public class Coffee {
    private String name;
    private List<Ingrediant> ingrediants;

    public Coffee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Ingrediant> getIngrediants() {
        return ingrediants;
    }

    public void addIngrediants(List<Ingrediant> ingrediants) {
        this.ingrediants = ingrediants;
    }

}
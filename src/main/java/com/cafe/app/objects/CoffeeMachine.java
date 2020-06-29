package com.cafe.app.objects;

import java.util.HashMap;
import java.util.Map;

import com.cafe.app.exception.ApiException;

public class CoffeeMachine {
    private String name;
    private transient Inventory inventory;
    private Integer outlets;
    private transient Map<String, Coffee> recipes;

    public CoffeeMachine(String name, Integer outlets) {
        this.name = name;
        this.outlets = outlets;
        this.inventory = new Inventory();
        this.recipes = new HashMap<>();
    }

    public Integer getOutlets() {
        return outlets;
    }

    public void setOutlets(Integer outlets) {
        this.outlets = outlets;
    }

    public String getName() {
        return this.name;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void addInventory(Ingrediant ingrediant) {
        this.inventory.addStock(ingrediant);
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void addRecipe(Coffee coffee) {
        this.recipes.put(coffee.getName(), coffee);
    }

    public Coffee makeCoffee(String name) throws ApiException {
        if (!recipes.containsKey(name)) {
            throw new ApiException("Unknown recipe. Can not make coffee");
        }
        Coffee coffee = recipes.get(name);
        this.inventory.getIngrediants(coffee.getIngrediants());
        return coffee;
    }
}
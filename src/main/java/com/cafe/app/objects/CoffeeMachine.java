package com.cafe.app.objects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cafe.app.exception.ApiException;

public class CoffeeMachine {
    private String name;
    private transient Inventory inventory;
    private Integer outlets;
    private Map<String, Coffee> recipes;
    private transient Integer availableOutlets;

    public CoffeeMachine(String name, Integer outlets) {
        this.name = name;
        this.outlets = outlets;
        this.availableOutlets = outlets;
        this.inventory = new Inventory(100);
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

    public void addInventory(Ingrediant ingrediant) throws ApiException {
        this.inventory.addStock(ingrediant);
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void addRecipe(Coffee coffee) {
        this.recipes.put(coffee.getName(), coffee);
    }

    private void reserveOutlet() throws ApiException {
        synchronized (this.availableOutlets) {
            if (this.availableOutlets <= 0) {
                throw new ApiException("No outlet available.");
            }
            this.availableOutlets--;
        }
    }

    private void releaseOutlet() {
        synchronized (this.availableOutlets) {
            this.availableOutlets++;
        }
    }

    public Coffee makeCoffee(String name) throws ApiException {
        if (!recipes.containsKey(name)) {
            throw new ApiException("Unknown recipe. Can not make coffee");
        }
        reserveOutlet();
        Coffee coffee = recipes.get(name);
        try {
            this.inventory.getIngrediants(coffee.getIngrediants());
        } finally {
            releaseOutlet();
        }
        return coffee;
    }

    public List<Ingrediant> checkLowStock() throws ApiException {
        return this.inventory.getLowStocks();
    }
}
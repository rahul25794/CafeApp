package com.cafe.app.objects;

import java.util.List;
import java.util.Map;

import com.cafe.app.exception.ApiException;

public class Inventory {
    private Map<String, Ingrediant> stock;

    public Map<String, Ingrediant> getStock() {
        return stock;
    }

    public void addStock(Ingrediant ingrediant) {
        Ingrediant i;
        if (!this.stock.containsKey(ingrediant.getName())) {
            i = this.stock.get(ingrediant.getName());
        } else {
            i = new Ingrediant();
            i.setName(ingrediant.getName());
            i.setQuantity(0d);
        }
        i.setQuantity(i.getQuantity() + ingrediant.getQuantity());
        this.stock.put(i.getName(), i);
    }

    private void checkAvailability(Ingrediant ingrediant) throws ApiException {
        if (!stock.containsKey(ingrediant.getName())
                || stock.get(ingrediant.getName()).getQuantity() - ingrediant.getQuantity() < 0) {
            throw new ApiException(ingrediant.getName() + " is put of stock");
        }
    }

    private void getIngrediant(Ingrediant ingrediant) {
        Double quantity = stock.get(ingrediant.getName()).getQuantity() - ingrediant.getQuantity();
        stock.get(ingrediant.getName()).setQuantity(quantity);
    }

    public void getIngrediants(List<Ingrediant> ingrediants) throws ApiException {
        synchronized (this) {
            for (Ingrediant i : ingrediants) {
                checkAvailability(i);
            }
            for (Ingrediant i : ingrediants) {
                getIngrediant(i);
            }
        }
    }
}
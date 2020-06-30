package com.cafe.app.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cafe.app.exception.ApiException;

public class Inventory {
    private transient Map<String, Ingrediant> stock;
    private transient Integer capacity;
    private transient Double lowStockLimitPercent = 20d;

    public Inventory(Integer capacity) {
        stock = new HashMap<>();
        this.capacity = capacity;
    }

    public Map<String, Ingrediant> getStock() {
        return stock;
    }

    public void addStock(Ingrediant ingrediant) throws ApiException {
        synchronized (this.stock) {
            if (stock.size() == capacity) {
                throw new ApiException("Inventory Full");
            }
            Ingrediant i;
            if (this.stock.containsKey(ingrediant.getName())) {
                i = this.stock.get(ingrediant.getName());
            } else {
                i = new Ingrediant(ingrediant.getName(), 0d);
            }
            i.setQuantity(i.getQuantity() + ingrediant.getQuantity());
            this.stock.put(i.getName(), i);
        }
    }

    private void checkAvailability(Ingrediant ingrediant) throws ApiException {
        if (!stock.containsKey(ingrediant.getName())
                || stock.get(ingrediant.getName()).getQuantity() - ingrediant.getQuantity() < 0) {
            throw new ApiException(ingrediant.getName() + " is out of stock");
        }
    }

    private void getIngrediant(Ingrediant ingrediant) {
        Double quantity = stock.get(ingrediant.getName()).getQuantity() - ingrediant.getQuantity();
        stock.get(ingrediant.getName()).setQuantity(quantity);
    }

    public void getIngrediants(List<Ingrediant> ingrediants) throws ApiException {
        synchronized (this.stock) {
            for (Ingrediant i : ingrediants) {
                checkAvailability(i);
            }
            for (Ingrediant i : ingrediants) {
                getIngrediant(i);
            }
        }
    }

    public List<Ingrediant> getLowStocks() {
        List<Ingrediant> returnList = new ArrayList<>();
        for (String name : stock.keySet()) {
            Double q = stock.get(name).getQuantity();
            if (q <= (capacity / 100) * lowStockLimitPercent) {
                returnList.add(stock.get(name));
            }
        }
        return returnList;
    }
}
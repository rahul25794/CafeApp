package com.cafe.app.objects;

public class Ingrediant {
    private String name;
    private Double quantity;

    public String getName() {
        return name;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Ingrediant(String name, Double quantity) {
        this.name = name;
        this.quantity = quantity;
    }

}
package com.cafe.app.service;

import java.util.ArrayList;
import java.util.List;

import com.cafe.app.exception.ApiException;
import com.cafe.app.objects.Coffee;
import com.cafe.app.objects.Ingrediant;

import org.json.JSONArray;

public class RecipeService {
    public static Coffee createRecipe(String name, JSONArray ingrediantArr) throws ApiException {
        Coffee coffee = new Coffee(name);
        List<Ingrediant> ingrediants = new ArrayList<>();
        for (int i = 0; i < ingrediantArr.length(); i++) {
            ingrediants.add(new Ingrediant(ingrediantArr.getJSONObject(i).getString("name"),
                    ingrediantArr.getJSONObject(i).getDouble("quantity")));
        }
        coffee.setIngrediants(ingrediants);
        return coffee;
    }
}
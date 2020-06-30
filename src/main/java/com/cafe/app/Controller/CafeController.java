package com.cafe.app.Controller;

import java.util.List;

import com.cafe.app.objects.Coffee;
import com.cafe.app.objects.CoffeeMachine;
import com.cafe.app.objects.Ingrediant;
import com.cafe.app.service.MachineService;
import com.cafe.app.service.RecipeService;

import org.json.JSONArray;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CafeController.BASE_URL)
public class CafeController {
    public static final String BASE_URL = "";

    @PostMapping()
    public CoffeeMachine addMachine(@RequestParam(required = true) String name,
            @RequestParam(required = true) Integer numberOfOutlets) throws Exception {
        CoffeeMachine machine = new CoffeeMachine(name, numberOfOutlets);
        return MachineService.addMachine(machine);
    }

    @GetMapping(value = "/{name}")
    public CoffeeMachine get(@PathVariable(value = "name", required = true) String name) throws Exception {
        return MachineService.getMachine(name);
    }

    @GetMapping(value = "/{name}/{coffeeName}")
    public Coffee get(@PathVariable(value = "name", required = true) String name,
            @PathVariable(value = "coffeeName", required = true) String coffeeName) throws Exception {
        return MachineService.getMachine(name).makeCoffee(coffeeName);
    }

    @PostMapping(value = "/{machine}/stock")
    public CoffeeMachine addStock(@PathVariable(value = "machine", required = true) String machine,
            @RequestParam(required = true) String name, @RequestParam(required = true) Double quantity)
            throws Exception {
        return MachineService.addIngrediant(machine, new Ingrediant(name, quantity));
    }

    @GetMapping(value = "/{machine}/stock")
    public List<Ingrediant> getLowStock(@PathVariable(value = "machine", required = true) String machine)
            throws Exception {
        return MachineService.getMachine(machine).checkLowStock();
    }

    @PostMapping(value = "/{machine}/recipe")
    public CoffeeMachine addStock(@PathVariable(value = "machine", required = true) String machine,
            @RequestParam(required = true) String name, @RequestParam(required = true) String ingrediants)
            throws Exception {
        JSONArray arr = new JSONArray(ingrediants);
        Coffee coffee = RecipeService.createRecipe(name, arr);
        return MachineService.addRecipe(machine, coffee);
    }

    @GetMapping()
    public List<CoffeeMachine> getAllMachine() throws Exception {
        return MachineService.getAllMachine();
    }
}
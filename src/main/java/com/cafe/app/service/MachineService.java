package com.cafe.app.service;

import java.util.ArrayList;
import java.util.List;

import com.cafe.app.exception.ApiException;
import com.cafe.app.objects.Coffee;
import com.cafe.app.objects.CoffeeMachine;
import com.cafe.app.objects.Ingrediant;
import com.cafe.app.persistant.StorageFactory;

public class MachineService {
    public static CoffeeMachine addMachine(CoffeeMachine machine) throws ApiException {
        StorageFactory.get().storeMachine(machine);
        return StorageFactory.get().getMachine(machine.getName());
    }

    public static CoffeeMachine getMachine(String name) throws ApiException {
        return StorageFactory.get().getMachine(name);
    }

    public static List<CoffeeMachine> getAllMachine() {
        return StorageFactory.get().getAllMachine();
    }

    public static CoffeeMachine addIngrediant(String machineName, Ingrediant ingrediant) throws ApiException {
        CoffeeMachine m = MachineService.getMachine(machineName);
        m.addInventory(ingrediant);
        return m;
    }

    public static CoffeeMachine addRecipe(String machineName, Coffee coffee) throws ApiException {
        CoffeeMachine m = MachineService.getMachine(machineName);
        m.addRecipe(coffee);
        return m;
    }
}
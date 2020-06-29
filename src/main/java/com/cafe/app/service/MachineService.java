package com.cafe.app.service;

import java.util.ArrayList;
import java.util.List;

import com.cafe.app.objects.CoffeeMachine;
import com.cafe.app.persistant.StorageFactory;

public class MachineService {
    public static CoffeeMachine addMachine(String name, Integer outlets) {
        StorageFactory.get().storeMachine(new CoffeeMachine(name, outlets));
        return StorageFactory.get().getMachine(name);
    }

    public static List<CoffeeMachine> getAllMachine() {
        return new ArrayList<>();
    }
}
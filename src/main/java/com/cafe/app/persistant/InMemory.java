package com.cafe.app.persistant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cafe.app.exception.ApiException;
import com.cafe.app.objects.CoffeeMachine;

public class InMemory implements Storage {
    private static InMemory obj;
    private Map<String, CoffeeMachine> storage;

    private InMemory() {
        storage = new HashMap<>();
    }

    public static InMemory get() {
        if (obj == null) {
            obj = new InMemory();
        }
        return obj;
    }

    @Override
    public void storeMachine(CoffeeMachine machine) {
        storage.put(machine.getName(), machine);
    }

    @Override
    public CoffeeMachine getMachine(String name) throws ApiException {
        if (!storage.containsKey(name)) {
            throw new ApiException("No coffee machine with this name.");
        }
        return storage.get(name);
    }

    @Override
    public List<CoffeeMachine> getAllMachine() {
        return new ArrayList<>(storage.values());
    }

}
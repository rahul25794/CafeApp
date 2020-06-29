package com.cafe.app.persistant;

import java.util.List;

import com.cafe.app.objects.CoffeeMachine;

public interface Storage {
    public void storeMachine(CoffeeMachine machine);

    public CoffeeMachine getMachine(String name);

    public List<CoffeeMachine> getAllMachine();
}
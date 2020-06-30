package com.cafe.app.persistant;

import java.util.List;

import com.cafe.app.exception.ApiException;
import com.cafe.app.objects.CoffeeMachine;

public interface Storage {
    public void storeMachine(CoffeeMachine machine) throws ApiException;

    public CoffeeMachine getMachine(String name) throws ApiException;

    public List<CoffeeMachine> getAllMachine();
}
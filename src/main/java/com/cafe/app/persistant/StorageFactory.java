package com.cafe.app.persistant;

public class StorageFactory {
    public static Storage get() {
        // db persistant can be added here
        return InMemory.get();
    }
}
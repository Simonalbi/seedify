package com.unisa.seedify.model;

import java.util.ArrayList;
import java.util.HashMap;

public class EntityPrimaryKey {
    private HashMap<String, Object> keys;

    public EntityPrimaryKey() {
        this.keys = new HashMap<>();
    }

    public EntityPrimaryKey addKey(String key, Object value) {
        keys.put(key, value);
        return this;
    }

    public EntityPrimaryKey deleteKey(String key) {
        keys.remove(key);
        return this;
    }

    public Object getKey(String key) {
        return keys.get(key);
    }

    public HashMap<String, Object> getKeys() {
        return new HashMap<>(this.keys);
    }

    @Override
    public String toString() {
        ArrayList<String> keysValuesPairs = new ArrayList<>();
        for (String key : this.keys.keySet()) {
            keysValuesPairs.add(key + "=" + keys.get(key));
        }
        return String.join(",", keysValuesPairs);
    }
}

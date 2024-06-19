package com.unisa.seedify.model;

import java.util.HashMap;

public class EntityPrimaryKey {
    private HashMap<String, Object> keys;

    public EntityPrimaryKey() {
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
        return keys.toString();
    }
}

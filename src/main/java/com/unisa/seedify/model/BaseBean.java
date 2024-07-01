package com.unisa.seedify.model;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class BaseBean {
    public EntityPrimaryKey getPrimaryKey() {
        throw new UnsupportedOperationException();
    }

    public static EntityPrimaryKey parsePrimaryKey(String rawEntityPrimaryKey) {
        EntityPrimaryKey entityPrimaryKey = new EntityPrimaryKey();
        ArrayList<String> entityPrimaryKeyValues = new ArrayList<>(Arrays.asList(rawEntityPrimaryKey.split(",")));
        for (String value : entityPrimaryKeyValues) {
            String[] keyValue = value.split("=");

            Object effectiveValue;
            try {
                effectiveValue = Integer.parseInt(keyValue[1]);
            } catch (NumberFormatException ignored) {
                try {
                    effectiveValue = Float.parseFloat(keyValue[1]);
                } catch (NumberFormatException ignored2) {
                    effectiveValue = keyValue[1];
                }
            }

            entityPrimaryKey.addKey(keyValue[0], effectiveValue);
        }

        return entityPrimaryKey;
    }
}

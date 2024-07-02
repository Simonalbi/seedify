package com.unisa.seedify.model;

import com.google.gson.annotations.SerializedName;

public enum States {
    @SerializedName("ATTIVO")
    ACTIVE("ATTIVO"),

    @SerializedName("ELIMINATO")
    DELETED("ELIMINATO");


    private final String translation;
    States(String translation) {
        this.translation = translation;
    }

    @Override
    public String toString() {
        return this.translation;
    }

    public static States fromString(String translation) {
        for (States state : States.values()) {
            if (state.translation.equals(translation)) {
                return state;
            }
        }
        return null;
    }
}

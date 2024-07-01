package com.unisa.seedify.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MemorizationsBean extends BaseBean implements Serializable {
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

        public static MemorizationsBean.States fromString(String translation) {
            for (MemorizationsBean.States state : MemorizationsBean.States.values()) {
                if (state.translation.equals(translation)) {
                    return state;
                }
            }
            return null;
        }
    }
    private static final long serialVersionUID = 1L;

    private UserBean user;
    private List<CreditCardBean> creditCards;

    @SerializedName("stato")
    private MemorizationsBean.States state;

    public MemorizationsBean() {
        this.creditCards = new ArrayList<>();
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public List<CreditCardBean> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(List<CreditCardBean> creditCards) {
        this.creditCards = creditCards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemorizationsBean that = (MemorizationsBean) o;
        return Objects.equals(user, that.user) && Objects.equals(creditCards, that.creditCards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, creditCards);
    }
}
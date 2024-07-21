package com.unisa.seedify.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MemorizationsBean extends BaseBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @SerializedName("utente")
    private UserBean user;

    @SerializedName("carte_di_credito")
    private List<CreditCardBean> creditCards;

    public MemorizationsBean() {
        this.creditCards = new ArrayList<>();
    }

    public MemorizationsBean(UserBean user, List<CreditCardBean> creditCards) {
        this.user = user;
        this.creditCards = creditCards;
    }

    public UserBean getUser() {
        return user;
    }

    protected void setUser(UserBean user) {
        this.user = user;
    }

    public List<CreditCardBean> getCreditCards() {
        return creditCards;
    }

    protected void setCreditCards(List<CreditCardBean> creditCards) {
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
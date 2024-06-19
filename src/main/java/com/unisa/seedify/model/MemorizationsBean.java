package com.unisa.seedify.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MemorizationsBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private UserBean user;
    private List<CreditCardBean> creditCards;

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
package com.unisa.seedify.model;

import java.io.Serializable;
import java.util.List;

public class MemorizationsBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private UserBean user;
    private List<CreditCardBean> creditCards;

    public MemorizationsBean() {
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
}
package com.unisa.seedify.model;

import java.io.Serializable;
import java.util.List;

public class LocationsBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private UserBean user;
    private List<AddressBean> address;

    public LocationsBean() {
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public List<AddressBean> getAddress() {
        return address;
    }

    public void setAddress(List<AddressBean> address) {
        this.address = address;
    }
}

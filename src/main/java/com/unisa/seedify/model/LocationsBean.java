package com.unisa.seedify.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationsBean that = (LocationsBean) o;
        return Objects.equals(user, that.user) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, address);
    }
}

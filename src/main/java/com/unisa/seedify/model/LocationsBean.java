package com.unisa.seedify.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LocationsBean extends BaseBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private UserBean user;
    private List<AddressBean> addresses;

    public LocationsBean() {
        this.addresses = new ArrayList<>();
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public List<AddressBean> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressBean> addresses) {
        this.addresses = addresses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationsBean that = (LocationsBean) o;
        return Objects.equals(user, that.user) && Objects.equals(addresses, that.addresses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, addresses);
    }
}

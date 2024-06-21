package com.unisa.seedify.model;

import java.io.Serializable;
import java.util.Objects;

public class UserBean implements Serializable {
    public enum Roles {
        ADMIN("AMMINISTRATORE"),
        EMPLOYEE("DIPENDENTE"),
        CLIENT("CLIENTE");

        private final String translation;

        Roles(String translation) {
            this.translation = translation;
        }

        @Override
        public String toString() {
            return this.translation;
        }

        public static Roles fromString(String translation) {
            for (Roles role : Roles.values()) {
                if (role.translation.equals(translation)) {
                    return role;
                }
            }
            return null;
        }
    }

    private static final long serialVersionUID = 1L;

    private String email;
    private String password;
    private byte[] profilePicture;
    private String name;
    private String surname;
    private Roles role;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBean userBean = (UserBean) o;
        return Objects.equals(email, userBean.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }
}

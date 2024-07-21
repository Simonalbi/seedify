package com.unisa.seedify.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

public class UserBean extends BaseBean implements Serializable {
    public enum Roles {
        @SerializedName("AMMINISTRATORE")
        ADMIN("AMMINISTRATORE"),

        @SerializedName("CLIENTE")
        CUSTOMER("CLIENTE");

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

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("foto_profilo")
    private byte[] profilePicture;

    @SerializedName("nome")
    private String name;

    @SerializedName("cognome")
    private String surname;

    @SerializedName("ruolo")
    private Roles role;

    public UserBean() {
    }

    public UserBean(String email, String password, byte[] profilePicture, String name, String surname, Roles role) {
        this.email = email;
        this.password = password;
        this.profilePicture = profilePicture;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    protected void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    protected void setPassword(String password) {
        this.password = password;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    protected void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    protected void setSurname(String surname) {
        this.surname = surname;
    }

    public Roles getRole() {
        return role;
    }

    protected void setRole(Roles role) {
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

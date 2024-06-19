package com.unisa.seedify.control.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class InputValidation {
    public static boolean isEmailValid(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }

    public static boolean isPasswordStrong(String password) {
        String regex = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$#@&.%!*/]).{8,}";
        return password.matches(regex);
    }

    public static boolean isNameValid(String name) {
        return name.matches("^([a-zA-Z\\s]+)$");
    }

    public static String sha256(String password) {
        String sha256 = null;

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append("0");
                }
                hexString.append(hex);
            }
            sha256 = hexString.toString();
        } catch (NoSuchAlgorithmException ignored) {}

        return sha256;
    }
}

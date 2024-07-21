package com.unisa.seedify.utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.util.Base64;

public class SecurityUtils {
    public static String encrypt(String data, String key) {
        // Verifica che la chiave sia della lunghezza corretta per AES
        if (key.length() != 16 && key.length() != 24 && key.length() != 32) {
            throw new IllegalArgumentException("Encryption key must be 16, 24, or 32 bytes long");
        }

        Key secretKey = new SecretKeySpec(key.getBytes(), "AES");

        // Genera un Initialization Vector (IV)
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Configurazione del cifrario AES in modalità CBC con PKCS5Padding
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        }

        // Cifratura della stringa
        byte[] encryptedBytes = null;
        try {
            encryptedBytes = cipher.doFinal(data.getBytes());
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }

        // Converti l'IV e i byte cifrati in una stringa base64
        byte[] ivAndEncryptedBytes = new byte[iv.length + encryptedBytes.length];
        System.arraycopy(iv, 0, ivAndEncryptedBytes, 0, iv.length);
        System.arraycopy(encryptedBytes, 0, ivAndEncryptedBytes, iv.length, encryptedBytes.length);

        return Base64.getEncoder().encodeToString(ivAndEncryptedBytes);
    }

    public static String decrypt(String encryptedData, String key) {
        // Verifica che la chiave sia della lunghezza corretta per AES
        if (key.length() != 16 && key.length() != 24 && key.length() != 32) {
            throw new IllegalArgumentException("Encryption key must be 16, 24, or 32 bytes long");
        }

        Key secretKey = new SecretKeySpec(key.getBytes(), "AES");

        // Decodifica la stringa base64
        byte[] ivAndEncryptedBytes = Base64.getDecoder().decode(encryptedData);

        // Estrai l'IV e i byte cifrati
        byte[] iv = new byte[16];
        byte[] encryptedBytes = new byte[ivAndEncryptedBytes.length - iv.length];
        System.arraycopy(ivAndEncryptedBytes, 0, iv, 0, iv.length);
        System.arraycopy(ivAndEncryptedBytes, iv.length, encryptedBytes, 0, encryptedBytes.length);

        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Configurazione del cifrario AES in modalità CBC con PKCS5Padding
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        }

        // Decifra i byte
        byte[] decryptedBytes = null;
        try {
            decryptedBytes = cipher.doFinal(encryptedBytes);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }

        // Restituisce la stringa decifrata, rimuovendo eventuali spazi di padding
        return new String(decryptedBytes).trim();
    }
}

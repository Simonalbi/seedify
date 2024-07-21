package com.unisa.seedify.model;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.security.*;
import java.util.Base64;

public abstract class BaseDao {
    protected static DataSource dataSource;
    static {
        try {
            Context initialContext = new InitialContext();
            Context enviromentContext = (Context) initialContext.lookup("java:comp/env");
            dataSource = (DataSource) enviromentContext.lookup("jdbc/seedify");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String ENCRYPTION_KEY = "8!\\)r9bg$/hH?[RcF5cqBc4d9bm01h%A";

    protected static String encrypt(String data) {
        Key secretKey = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), "AES");

        // Genera un Initialization Vector (IV)
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Configurazione del cifrario AES in modalità CBC con PKCS5Padding
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                 InvalidAlgorithmParameterException e) {
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

    protected static String decrypt(String encryptedData) {
        // Verifica che la chiave sia della lunghezza corretta per AES
        if (ENCRYPTION_KEY.length() != 16 && ENCRYPTION_KEY.length() != 24 && ENCRYPTION_KEY.length() != 32) {
            throw new IllegalArgumentException("Encryption key must be 16, 24, or 32 bytes long");
        }

        Key secretKey = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), "AES");

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

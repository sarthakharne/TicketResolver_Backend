package com.had.selfhelp.configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.SerializationUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
//import javax.persistence.AttributeConverter;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.Base64;

/*This Java code represents an AES (Advanced Encryption Standard) encryptor
that implements the AttributeConverter interface.
The purpose of this code seems to be to provide encryption and decryption functionality for attributes in a Java
application that interacts with a database via JPA (Java Persistence API). */

@Configuration
public class AesEncryptor implements AttributeConverter<Object, String> {

    @Value("${aes.encryption.key}")
    private String encryptionKey;

    private final String encryptionCipher = "AES";

    private Key key;
    private Cipher cipher;

    private Key getKey() {
        if (key == null)
            key = new SecretKeySpec(encryptionKey.getBytes(), encryptionCipher);
        return key;
    }

    private Cipher getCipher() throws GeneralSecurityException {
        if (cipher == null)
            cipher = Cipher.getInstance(encryptionCipher);
        return cipher;
    }

    private void initCipher(int encryptMode) throws GeneralSecurityException {
        getCipher().init(encryptMode, getKey());
    }

    @Override
    public String convertToDatabaseColumn(Object attribute) {
        if (attribute == null)
            return null;
        try {
            initCipher(Cipher.ENCRYPT_MODE);
            byte[] bytes = SerializationUtils.serialize(attribute);
            return Base64.getEncoder().encodeToString(getCipher().doFinal(bytes));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    @Override
    public Object convertToEntityAttribute(String dbData) {
        if (dbData == null)
            return null;
        try {
            initCipher(Cipher.DECRYPT_MODE);
            byte[] bytes = getCipher().doFinal(Base64.getDecoder().decode(dbData));
            return SerializationUtils.deserialize(bytes);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
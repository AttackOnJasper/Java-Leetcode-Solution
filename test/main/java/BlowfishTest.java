package main.java;

import static junit.framework.TestCase.assertEquals;

import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import org.junit.Test;

/**
 * Created by jasperwang on 2018-01-21.
 */
public class BlowfishTest {
    // create a key generator based upon the Blowfish cipher
    private final KeyGenerator keygenerator;

    // create a key
    private SecretKey secretkey;

    public BlowfishTest() throws NoSuchAlgorithmException {
        keygenerator = KeyGenerator.getInstance("Blowfish");
        secretkey = keygenerator.generateKey();
    }

    @Test
    public void test() throws Exception {
        byte[] encrypted = encrypt("password");

        System.out.println(new String(encrypted));

        byte[] message = decrypt(encrypted);

        assertEquals("password", new String(message));
    }

    private byte[] encrypt(String password) throws Exception {
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.ENCRYPT_MODE, secretkey);
        return cipher.doFinal(password.getBytes());
    }

    private byte[] decrypt(byte[] string) throws Exception {
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.DECRYPT_MODE, secretkey);
        return cipher.doFinal(string);
    }
}
package main.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;
import org.junit.Test;

/**
 * Created by jasperwang on 2018-01-21.
 */
public class MD5Test {
    @Test
    public void givenPassword_whenHashing_thenVerifying()
        throws NoSuchAlgorithmException {
        String hash = "35454B055CC325EA1AF2126E27707052";
        String password = "ILoveJava";

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter
            .printHexBinary(digest).toUpperCase();

        assertTrue(myHash.equals(hash));
    }

    @Test
    public void test() {
        String message = "password";
        String encrypted = encrypt(message);
        System.out.println(encrypted);
        assertEquals(message, decrypt(encrypted));
    }

    private String encrypt(String message) {
        char[] temp = message.toCharArray();
        for (int i = 0; i < temp.length; i++) {
            temp[i] += 5;
        }
        return new String(temp);
    }

    private String decrypt(String encrypt) {
        char[] temp = encrypt.toCharArray();
        for (int i = 0; i < temp.length; i++) {
            temp[i] -= 5;
        }
        return new String(temp);
    }
}

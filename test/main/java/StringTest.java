package main.java;

import static junit.framework.TestCase.assertEquals;

import org.junit.Test;

/**
 * Created by jasperwang on 2017-11-27.
 */
public class StringTest {
    @Test
    public void testCipherEncode() {
        String s = "w3579";
        assertEquals("f3579", StringQuestion.cipherEncode(s, 9));
        s = "joqierjTejrqoiREJIOQ";
        System.out.println(StringQuestion.cipherEncode(s, 9));
    }

    @Test
    public void testCipherDecode() {
        String s = "f3579";
        assertEquals("w3579", StringQuestion.cipherDecode(s, 9));
        s = "sxzrnasCnsazxrANSRXZ";
        System.out.println(StringQuestion.cipherDecode(s, 9));
    }
}

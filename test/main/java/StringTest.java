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
        assertEquals("sxzrnasCnsazxrANSRXZ", StringQuestion.cipherEncode(s, 9));
        s = "Wdc519jasper";
        System.out.println(StringQuestion.cipherEncode(s, 9));
    }

    @Test
    public void testCipherDecode() {
        String s = "f3579";
        assertEquals("w3579", StringQuestion.cipherDecode(s, 9));
        s = "sxzrnasCnsazxrANSRXZ";
        assertEquals("joqierjTejrqoiREJIOQ", StringQuestion.cipherDecode(s, 9));
        s = "781Fml@519";
        System.out.println(StringQuestion.cipherDecode(s, 9));
    }
}

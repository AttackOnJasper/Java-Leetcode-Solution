package main.java;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by jasperwang on 2017-11-27.
 */
public class StringTest {
    private StringQuestion stringQuestion;
    @Before
    public void setUp() {
        stringQuestion = new StringQuestion();
    }
    // 20
    @Test
    public void testIsValid() {
        assertTrue(stringQuestion.isValid("()"));
    }

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

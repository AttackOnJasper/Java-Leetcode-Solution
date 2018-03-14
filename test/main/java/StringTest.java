package main.java;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

import junit.framework.TestCase;
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

    @Test
    public void testTernaryParser() {
        assertEquals("F", stringQuestion.parseTernary("T?T?F:5:3"));
    }

    @Test
    public void testNextClosetTimeII() {
        TestCase.assertTrue("23:59".equals(stringQuestion.nextClosestTimeII("23:59")));
        TestCase.assertTrue("16:59".equals(stringQuestion.nextClosestTimeII("19:56")));
        TestCase.assertTrue("00:11".equals(stringQuestion.nextClosestTimeII("11:00")));
        TestCase.assertTrue("00:00".equals(stringQuestion.nextClosestTimeII("00:00")));
        TestCase.assertTrue("00:10".equals(stringQuestion.nextClosestTimeII("00:01")));
        TestCase.assertTrue("10:00".equals(stringQuestion.nextClosestTimeII("01:00")));
        TestCase.assertTrue("03:11".equals(stringQuestion.nextClosestTimeII("01:31")));
    }
}

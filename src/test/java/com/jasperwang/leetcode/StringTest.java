package com.jasperwang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created by jasperwang on 2017-11-27.
 */
public class StringTest {
    private StringQuestion stringQuestion;
    @BeforeEach
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

    // 422
    @Test
    public void testValidSquare() {
        final List<String> l = new ArrayList<>();
        l.add("abc");
        l.add("b");
        assertFalse(stringQuestion.validWordSquare(l));
    }

    // 443
    @Test
    public void testCompress() {
        assertEquals(4, stringQuestion.compress(new char[]{'a','b','b','b','b','b','b','b','b','b','b','b','b'}));
    }

    @Test
    public void testNextClosetTimeII() {
        assertEquals("23:59", stringQuestion.nextClosestTimeII("23:59"));
        assertEquals("16:59", stringQuestion.nextClosestTimeII("19:56"));
        assertEquals("00:11", stringQuestion.nextClosestTimeII("11:00"));
        assertEquals("00:00", stringQuestion.nextClosestTimeII("00:00"));
        assertEquals("00:10", stringQuestion.nextClosestTimeII("00:01"));
        assertEquals("10:00", stringQuestion.nextClosestTimeII("01:00"));
        assertEquals("03:11", stringQuestion.nextClosestTimeII("01:31"));
    }
}

package main.java;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MathQuestionTest {
    private MathQuestion mathQuestion;

    @BeforeEach
    public void setUp() {
        mathQuestion = new MathQuestion();
    }

    // 405
    @Test
    public void testToHex() {
        assertEquals("1a", mathQuestion.toHex(26));
    }

    // 461
    @Test
    public void testHammingDistance() {
        assertEquals(2, mathQuestion.hammingDistance(1, 4));
    }

    // 476
    @Test
    public void testFindComplement() {
        assertEquals(2, mathQuestion.findComplement(5));
        assertEquals(0, mathQuestion.findComplement(15));
        assertEquals(55, mathQuestion.findComplement(200));
        assertEquals(262143, mathQuestion.findComplement(262144));

        assertEquals(2, mathQuestion.findComplement2(5));
        assertEquals(0, mathQuestion.findComplement2(15));
        assertEquals(55, mathQuestion.findComplement2(200));
        assertEquals(262143, mathQuestion.findComplement2(262144));
    }
}

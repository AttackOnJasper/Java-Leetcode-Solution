package main.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DPTest {
    private DPQuestion dpQuestion;

    @BeforeEach
    public void setUp() {
        dpQuestion = new DPQuestion();
    }

    // 712
    @Test
    public void testMinimumDeleteSum() {
        assertEquals(231, dpQuestion.minimumDeleteSum("sea","eat"));
        assertEquals(403, dpQuestion.minimumDeleteSum("delete","leet"));
    }

    // 714
    @Test
    public void testBestTimeToBuyAndSellWithTransactionFee() {
        assertEquals(8, dpQuestion.maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2));
    }

    // 717
    @Test
    public void test() {
        assertFalse(DPQuestion.isOneBitCharacter(new int[]{1, 1, 1, 0}));
    }

    // 718
    @Test
    public void testFindLength() {
        assertEquals(2, dpQuestion.findLength(new int[]{0,1,1,1,1}, new int[]{1,0,1,0,1}));
    }

    @Test
    public void testDeleteAndEarn() {
        assertEquals(6, dpQuestion.deleteAndEarn(new int[]{3,4,2}));
        assertEquals(9, dpQuestion.deleteAndEarn(new int[]{2, 2, 3, 3, 3, 4}));
    }

    @Test
    public void testKthGrammar() {
        assertEquals(1, dpQuestion.kthGrammar(3, 3));
        assertEquals(1, dpQuestion.kthGrammar(4, 5));
        assertEquals(0, dpQuestion.kthGrammar(4, 6));
    }
}

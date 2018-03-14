package main.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

public class DPTest {
    private DPQuestion dpQuestion;

    @Before
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
}
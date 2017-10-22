package main.java;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DPTest {
    // 712
    @Test
    public void testMinimumDeleteSum() {
        assertEquals(231, DPQuestion.minimumDeleteSum("sea","eat"));
        assertEquals(403, DPQuestion.minimumDeleteSum("delete","leet"));
    }

    // 714
    @Test
    public void testBestTimeToBuyAndSellWithTransactionFee() {
        assertEquals(8, DPQuestion.maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2));
    }
}

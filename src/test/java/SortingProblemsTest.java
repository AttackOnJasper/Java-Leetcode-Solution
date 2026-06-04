package main.java;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SortingProblemsTest {
    private static final int[] testArr1 = new int[]{4, 1, 5, 3, 2};
    private static final int[] testLongArr = new int[]{123, 456, 3423, 543, 7657, 213, 54, 8, 6, 23,
        4, 7, 34, 42, 123, 23, 124, 454, 654, 1231};
    private static final ArrayQuestion arrayQuestion = new ArrayQuestion();

    @Test
    public void testMergeSort() {
        int[] arr = arrayQuestion.mergeSort(testArr1);
        assertEquals(1, arr[0]);
        assertEquals(2, arr[1]);
        assertEquals(3, arr[2]);
        assertEquals(4, arr[3]);
    }

    @Test
    public void testQuickSort() {
        int[] arr = arrayQuestion.quickSort(testArr1);
        assertEquals(1, arr[0]);
        assertEquals(2, arr[1]);
        assertEquals(3, arr[2]);
        assertEquals(4, arr[3]);
    }

    @Test
    public void testSelectK() {
        assertEquals(8, arrayQuestion.selectK(testLongArr, 3));
        assertEquals(54, arrayQuestion.selectK(testLongArr, 8));
    }
}

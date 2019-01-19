package main.java;

import static junit.framework.TestCase.assertEquals;

import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class ArrayQuestionTest {
    private ArrayQuestion arrayQuestion;

    @Before
    public void setUp() {
        arrayQuestion = new ArrayQuestion();
    }

    // 88
    @Test
    public void testMerge() {
        int[] arr = new int[]{1, 2, 3, 0, 0, 0};
        arrayQuestion.merge2(arr, 3, new int[]{2, 5, 6}, 3);
        assertEquals(1, arr[0]);
        assertEquals(2, arr[1]);
        assertEquals(2, arr[2]);
        assertEquals(3, arr[3]);
    }

    // 560
    @Test
    public void testPairSum() {
    /*
      int[] myIntArray = new int[3];
      int[] myIntArray = {1,2,3};
      int[] myIntArray = new int[]{1,2,3};
      String[] myStringArray = new String[3];
      String[] myStringArray = {"a","b","c"};
      String[] myStringArray = new String[]{"a","b","c"};
     */
        final int[] arr = {1, 2, 3, 4};
        assertEquals(2, arrayQuestion.subarraySum(arr, 3));
    }

    @Test
    public void testTopKElement() {
        int[] arr = new int[]{1,1,1,2,2,3};
        List<Integer> res = arrayQuestion.topKFrequent(arr, 2);
        assertEquals(2, res.size());
        assertEquals(1, (int)res.get(0));
        assertEquals(2, (int)res.get(1));
    }

    // 621
//    @Test
//    public void testTaskScheduler() {
//        char[] ops = new char[]{'A', 'B', 'A', 'A', 'B'};
//        int res = arrayQuestion.taskScheduler(ops, 2);
//        assertEquals(8, res);
//    }

    // 683 variation
    @Test
    public void testKEmptySlotsII() {
        int[] arr = new int[]{1, 2, 3};
        assertEquals(1, arrayQuestion.kEmptySlotsII(arr, 1));
        assertEquals(2, arrayQuestion.kEmptySlotsII(arr, 2));

        arr = new int[]{3, 1, 5, 4, 2};
        assertEquals(4, arrayQuestion.kEmptySlotsII(arr, 1));
        assertEquals(-1, arrayQuestion.kEmptySlotsII(arr, 2));
        assertEquals(4, arrayQuestion.kEmptySlotsII(arr, 3));
        assertEquals(-1, arrayQuestion.kEmptySlotsII(arr, 4));
        assertEquals(5, arrayQuestion.kEmptySlotsII(arr, 5));

        arr = new int[]{3, 7, 4, 1, 6, 2, 5};
        assertEquals(5, arrayQuestion.kEmptySlotsII(arr, 1));
        assertEquals(6, arrayQuestion.kEmptySlotsII(arr, 2));
        assertEquals(-1, arrayQuestion.kEmptySlotsII(arr, 3));
        assertEquals(6, arrayQuestion.kEmptySlotsII(arr, 4));
        assertEquals(-1, arrayQuestion.kEmptySlotsII(arr, 5));
        assertEquals(-1, arrayQuestion.kEmptySlotsII(arr, 6));
        assertEquals(7, arrayQuestion.kEmptySlotsII(arr, 7));

        arr = new int[]{3, 7, 4, 1, 6, 5, 2};
        assertEquals(6, arrayQuestion.kEmptySlotsII(arr, 1));
        assertEquals(5, arrayQuestion.kEmptySlotsII(arr, 2));
        assertEquals(-1, arrayQuestion.kEmptySlotsII(arr, 3));
        assertEquals(-1, arrayQuestion.kEmptySlotsII(arr, 4));
        assertEquals(6, arrayQuestion.kEmptySlotsII(arr, 5));
        assertEquals(-1, arrayQuestion.kEmptySlotsII(arr, 6));
        assertEquals(7, arrayQuestion.kEmptySlotsII(arr, 7));
    }

    // 697
    @Test
    public void testFindShortestSubArray() {
        int[] arr = new int[]{1,2,2,3,1};
        int res = ArrayQuestion.findShortestSubArray(arr);
        assertEquals(2, res);
        assertEquals("aaa", "aaa".split(" ")[0]);
    }

    // 724
    @Test
    public void testFindPivotIndex() {
        int[] arr1 = new int[]{1, 7, 3, 6, 5, 6};
        int index1 = arrayQuestion.pivotIndex(arr1);
        assertEquals(3, index1);

        int[] arr2 = new int[]{-1,-1,-1,-1,-1,0};
        int index2 = arrayQuestion.pivotIndex(arr2);
        assertEquals(2, index2);
    }
}

package main.java;

import static junit.framework.TestCase.assertEquals;

import java.util.List;
import org.junit.Test;

public class ArrayQuestionTest {
    final ArrayQuestion arrayQuestion = new ArrayQuestion();

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
        assertEquals(4, arrayQuestion.arrayPairSum(arr));
    }

    @Test
    public void testTopKElement() {
        int[] arr = new int[]{1,1,1,2,2,3};
        List<Integer> res = arrayQuestion.topKFrequent(arr, 2);
        assertEquals(2, res.size());
        assertEquals(1, (int)res.get(0));
        assertEquals(2, (int)res.get(1));
    }

    @Test
    public void testBaseballGame() {
        String[] ops = new String[]{"5","-2","4","C","D","9","+","+"};
        assertEquals(27, arrayQuestion.calPoints(ops));
    }

    // 621
    @Test
    public void testTaskScheduler() {
        char[] ops = new char[]{'A', 'B', 'A', 'A', 'B'};
        int res = ArrayQuestion.taskScheduler(ops, 2);
        assertEquals(8, res);
    }

    // 697
    @Test
    public void testFindShortestSubArray() {
        int[] arr = new int[]{1,2,2,3,1};
        int res = ArrayQuestion.findShortestSubArray(arr);
        assertEquals(2, res);
        assertEquals("aaa", "aaa".split(" ")[0]);
    }
}

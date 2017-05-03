package test.java;

import static junit.framework.TestCase.assertEquals;

import main.EasyQuestion;
import org.junit.Before;
import org.junit.Test;

public class EasyQuestionTest {

  final EasyQuestion easyQuestion = new EasyQuestion();

  @Before
  public void setUp() {
  }

  @Test
  public void testReverseString() {
    assertEquals("hello", easyQuestion.reverseString("olleh"));
    assertEquals("hello", easyQuestion.reverseString2("olleh"));
  }

  @Test
  public void testFindComplement() {
    assertEquals(2, easyQuestion.findComplement(5));
  }

  @Test
  public void testHammingDistance() {
    assertEquals(2, easyQuestion.hammingDistance(1, 4));
  }

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
    assertEquals(4, easyQuestion.arrayPairSum(arr));
  }
}

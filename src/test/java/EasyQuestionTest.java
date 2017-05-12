package test.java;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

import main.EasyQuestion;
import org.junit.Before;
import org.junit.Test;

public class EasyQuestionTest {

  final EasyQuestion easyQuestion = new EasyQuestion();

  @Before
  public void setUp() {
  }

  // 20
  @Test
  public void testIsValid() {
    assertTrue(easyQuestion.isValid("()"));
  }

  // 88
  @Test
  public void testMerge() {
    int[] arr = new int[]{1, 2, 3, 0, 0, 0};
    easyQuestion.merge2(arr, 3, new int[]{2, 5, 6}, 3);
    assertEquals(1, arr[0]);
    assertEquals(2, arr[1]);
    assertEquals(2, arr[2]);
    assertEquals(3, arr[3]);
  }

  // 290
  @Test
  public void testWordPattern() {
    assertTrue(easyQuestion.wordPattern("abba", "dog cat cat dog"));
  }

  @Test
  public void testReverseString() {
    assertEquals("hello", easyQuestion.reverseString("olleh"));
    assertEquals("hello", easyQuestion.reverseString2("olleh"));
  }


  // 405
  @Test
  public void testToHex() {
    assertEquals("1a", easyQuestion.toHex(26));
  }

  // 461
  @Test
  public void testHammingDistance() {
    assertEquals(2, easyQuestion.hammingDistance(1, 4));
  }

  // 476
  @Test
  public void testFindComplement() {
    assertEquals(2, easyQuestion.findComplement(5));
    assertEquals(0, easyQuestion.findComplement(15));
    assertEquals(55, easyQuestion.findComplement(200));
    assertEquals(262143, easyQuestion.findComplement(262144));

    assertEquals(2, easyQuestion.findComplement2(5));
    assertEquals(0, easyQuestion.findComplement2(15));
    assertEquals(55, easyQuestion.findComplement2(200));
    assertEquals(262143, easyQuestion.findComplement2(262144));
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
    assertEquals(4, easyQuestion.arrayPairSum(arr));
  }
}

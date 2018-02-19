package main.java;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class EasyQuestionTest {
  private EasyQuestion easyQuestion;

  @Before
  public void setUp() {
    easyQuestion = new EasyQuestion();
  }

  // 405
  @Test
  public void testToHex() {
    assertEquals("1a", easyQuestion.toHex(26));
  }

  // 422
  @Test
  public void testValidSquare() {
    final List<String> l = new ArrayList<>();
    l.add("abc");
    l.add("b");
    assertFalse(easyQuestion.validWordSquare(l));
  }

  // 443
  @Test
  public void test() {
    assertEquals(4, easyQuestion.compress(new char[]{'a','b','b','b','b','b','b','b','b','b','b','b','b'}));
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
}

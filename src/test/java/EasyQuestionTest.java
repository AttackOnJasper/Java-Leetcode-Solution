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
    assertEquals(easyQuestion.reverseString("olleh"), "hello");
    assertEquals("hello", easyQuestion.reverseString2("olleh"));
  }

  @Test
  public void testFindComplement() {
    assertEquals(easyQuestion.findComplement(5), 2);
  }

}

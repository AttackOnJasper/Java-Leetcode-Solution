package test.java;

import static junit.framework.TestCase.assertEquals;

import java.util.*;
import main.MediumQuestion;
import main.TreeNode;
import org.junit.Test;

public class MediumQuestionTest {

    @Test
    public void testTernaryParser() {
        assertEquals("F", MediumQuestion.parseTernary("T?T?F:5:3"));
    }

    @Test
    public void testShortestWordDistance() {
        assertEquals(1, MediumQuestion.shortestWordDistance(new String[]{"a", "a", "b", "b"}, "b", "b"));
    }
}

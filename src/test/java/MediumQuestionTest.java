package test.java;

import static junit.framework.TestCase.assertEquals;

import java.util.*;
import main.MediumQuestion;
import main.TreeNode;
import org.junit.Test;

public class MediumQuestionTest {
    @Test
    public void testTopKElement() {
        int[] arr = new int[]{1,1,1,2,2,3};
        List<Integer> res = MediumQuestion.topKFrequent(arr, 2);
        assertEquals(2, res.size());
        assertEquals(1, (int)res.get(0));
        assertEquals(2, (int)res.get(1));
    }

    @Test
    public void testTernaryParser() {
        assertEquals("F", MediumQuestion.parseTernary("T?T?F:5:3"));
    }

    @Test
    public void testShortestWordDistance() {
        assertEquals(1, MediumQuestion.shortestWordDistance(new String[]{"a", "a", "b", "b"}, "b", "b"));
    }

    @Test
    public void testLCS() {
        TreeNode t = new TreeNode(1);
        t.right = new TreeNode(2);
        assertEquals(2, MediumQuestion.longestConsecutive(t));
    }
}

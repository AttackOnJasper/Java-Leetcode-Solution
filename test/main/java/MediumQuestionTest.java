package main.java;

import static junit.framework.TestCase.assertEquals;

import java.util.*;
import org.junit.Test;

public class MediumQuestionTest {
    @Test
    public void testReverseSecondHalfList() {
        ArrayDeque<Integer> vals = new ArrayDeque<Integer>();
        vals.add(3);
        vals.add(5);
        vals.add(7);
        vals.add(8);
        vals.add(10);
        ListNode head = ListNode.listNodeFactory(vals);
        head = MediumQuestion.reverseSecondHalfList(head);
        assertEquals(3, head.val);
    }

    @Test
    public void testTernaryParser() {
        assertEquals("F", MediumQuestion.parseTernary("T?T?F:5:3"));
    }
}

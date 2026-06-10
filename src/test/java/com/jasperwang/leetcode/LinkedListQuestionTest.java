package com.jasperwang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LinkedListQuestionTest {

    private LinkedListQuestion linkedListQuestion;

    @BeforeEach
    public void setUp() {
        linkedListQuestion = new LinkedListQuestion();
    }

    @Test
    public void testReverseSecondHalfList() {
        ArrayDeque<Integer> vals = new ArrayDeque<Integer>();
        vals.add(3);
        vals.add(5);
        vals.add(7);
        vals.add(8);
        vals.add(10);
        LinkedListQuestion.ListNode head = LinkedListQuestion.ListNode.listNodeFactory(vals);
        head = linkedListQuestion.reverseSecondHalfList(head);
        assertEquals(3, head.val);
    }
}

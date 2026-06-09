package com.jasperwang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;

import org.junit.jupiter.api.Test;

public class TreeQuestionTest {

    @Test
    public void testLCS() {
        TreeQuestion.TreeNode t = new TreeQuestion.TreeNode(1);
        t.right = new TreeQuestion.TreeNode(2);
        assertEquals(2, TreeQuestion.longestConsecutive(t));
    }

    @Test
    public void testReverseSecondHalfList() {
        ArrayDeque<Integer> vals = new ArrayDeque<Integer>();
        vals.add(3);
        vals.add(5);
        vals.add(7);
        vals.add(8);
        vals.add(10);
        ListQuestion.ListNode head = ListQuestion.ListNode.listNodeFactory(vals);
        head = ListQuestion.reverseSecondHalfList(head);
        assertEquals(3, head.val);
    }
}

package com.jasperwang.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TreeQuestionTest {

    private TreeQuestion treeQuestion;

    @BeforeEach
    public void setUp() {
        treeQuestion = new TreeQuestion();
    }

    @Test
    public void testLCS() {
        TreeQuestion.TreeNode t = new TreeQuestion.TreeNode(1);
        t.right = new TreeQuestion.TreeNode(2);
        assertEquals(2, treeQuestion.longestConsecutive(t));
    }
}

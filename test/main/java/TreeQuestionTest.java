package main.java;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TreeQuestionTest {
    @Test
    public void testLCS() {
        TreeNode t = new TreeNode(1);
        t.right = new TreeNode(2);
        assertEquals(2, TreeQuestion.longestConsecutive(t));
    }

}

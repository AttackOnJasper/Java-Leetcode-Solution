package main.java;

import java.util.ArrayDeque;

public class ListNode {
    public int val;
    public ListNode next;

    ListNode(int x) { val = x; }
    ListNode(int x, ListNode next) {
        this.val = x;
        this.next = next;
    }

    public static ListNode listNodeFactory(ArrayDeque<Integer> vals) {
        if (vals.size() == 0) return null;
        return new ListNode(vals.pollFirst(), listNodeFactory(vals));
    }
}
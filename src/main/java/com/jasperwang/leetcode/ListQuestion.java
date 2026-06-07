package com.jasperwang.leetcode;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by jasperwang on 2018-02-18.
 */
public class ListQuestion {
    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int x) {
            val = x;
        }

        public ListNode(int x, ListNode next) {
            this.val = x;
            this.next = next;
        }

        public static ListNode listNodeFactory(ArrayDeque<Integer> vals) {
            if (vals.size() == 0) return null;
            return new ListNode(vals.pollFirst(), listNodeFactory(vals));
        }
    }

    public static ListNode reverseSecondHalfList(ListNode head) {
        if (head == null) return null;
        ListNode fast = head, slow = head, prev = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }
        prev.next = reverse(slow);
        return head;
    }

    private static ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    // 2. Add Two Numbers Linked List
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        ListNode temp = res;
        int sum = 0;
        while (l1 != null || l2 != null) {
            sum /= 10;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            temp.next = new ListNode(sum % 10);
            temp = temp.next;
        }
        if (sum / 10 == 1) temp.next = new ListNode(1);
        return res.next;
    }

    // 23 Merge K lists (Wish interview)
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        if (lists.length == 1) return lists[0];
        return mergeKListsHelper(lists, 0, lists.length - 1);
    }

    private ListNode mergeKListsHelper(ListNode[] lists, int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2;
            ListNode head1 = mergeKListsHelper(lists, low, mid);
            ListNode head2 = mergeKListsHelper(lists, mid + 1, high);
            return merge(head1, head2);
        }
        return lists[low];
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }

    public ListNode mergeKLists2(ListNode[] lists) {
        ListNode curr = null;
        ListNode head = null;
        if (lists == null || lists.length < 1) return null;
        PriorityQueue<ListNode> q =
                new PriorityQueue<ListNode>(
                        lists.length,
                        new Comparator<ListNode>() {
                            public int compare(ListNode l1, ListNode l2) {
                                return l1.val - l2.val;
                            }
                        });
        // Add all the list nodes to the min heap
        for (int i = 0; i < lists.length; ++i) {
            if (lists[i] != null) {
                q.offer(lists[i]);
            }
        }

        // append all nodes to head in the sorted order
        while (!q.isEmpty()) {
            ListNode tmp = q.poll();
            if (head == null) {

                head = new ListNode(tmp.val);
                curr = head;
            } else {
                ListNode p = new ListNode(tmp.val);
                p.next = null;
                curr.next = p;
                curr = p;
            }
            if (tmp.next != null) {
                q.offer(tmp.next);
            }
        }
        return head;
    }

    // 83
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        ListNode list = head;
        while (list.next != null) {
            if (list.val == list.next.val) {
                list.next = list.next.next;
            } else {
                list = list.next;
            }
        }
        return head;
    }

    // 160. Intersection of 2 lists
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode a = headA, b = headB;
        /**
         * note the change to headB when a == null; max iteration is length a + length b when the
         * lengths of lists are different
         */
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }

    // 203 Remove Linked List Elements
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }

    public ListNode removeElements2(ListNode head, int val) {
        if (head == null) return head;
        ListNode curr = head;
        while (curr.next != null) {
            if (curr.next.val == val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return head.val == val ? head.next : head;
    }

    // 206 Reverse Linked List
    public ListNode reverseList1(ListNode head) {
        return helper(head, null);
    }

    private ListNode helper(ListNode head, ListNode newHead) {
        if (head == null) return newHead;
        ListNode next = head.next;
        head.next = newHead;
        return helper(next, head);
    }

    public ListNode reverseList2(ListNode head) {
        /* iterative solution */
        ListNode newHead = null;
        while (head != null) {
            ListNode next = head.next; // store the next node for next iteration
            // append new list to the back of this node, and update the new head
            head.next = newHead;
            newHead = head;
            // move to next node
            head = next;
        }
        return newHead;
    }

    // 234  Palindrome Linked List (in O(n) time and O(1) space)
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        if (fast != null) { // odd nodes: let right half smaller
            slow = slow.next;
        }
        slow = reverseList1(slow);
        fast = head;

        while (slow != null) {
            if (fast.val != slow.val) {
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }

    // 369
    private int carry = 1;

    public ListNode plusOne(ListNode head) {
        if (plusOneHelper(head).val != 0) return head;
        final ListNode newHead = new ListNode(1);
        newHead.next = head;
        return newHead;
    }

    private ListNode plusOneHelper(ListNode head) {
        if (head == null) return null;
        head.next = plusOneHelper(head.next);
        if (carry == 1) {
            if (head.val != 9) {
                head.val++;
                carry = 0;
                return head;
            }
            head.val = 0;
        }
        return head;
    }

    public ListNode plusOne2(ListNode head) {
        // add a new node for the case 999 + 1
        final ListNode dummy = new ListNode(0);
        dummy.next = head;
        // record the least significant digit that is not 9
        ListNode lastNotNine = dummy, node = head;

        while (node != null) {
            if (node.val != 9) {
                lastNotNine = node;
            }
            node = node.next;
        }
        lastNotNine.val++;
        node = lastNotNine.next;
        while (node != null) {
            node.val = 0;
            node = node.next;
        }
        return dummy.val == 1 ? dummy : dummy.next;
    }

    // 382

    /**
     * Reservoir Sampling: choose k elements from an array with unknown length
     */
    public class Solution {
        ListNode head;
        java.util.Random randomGen = null;

        /**
         * @param head The linked list's head. Note that the head is guaranteed to be not null, so it
         *             contains at least one node.
         */
        public Solution(ListNode head) {
            this.head = head;
            this.randomGen = new java.util.Random();
        }

        /**
         * Returns a random node's value.
         */
        public int getRandom() {
            ListNode res = null, curr = head;
            for (int i = 1; curr != null; i++) {
                if (randomGen.nextInt(i) == 0) { // returns a number between 0 and i - 1
                    res = curr;
                }
                curr = curr.next;
            }
            return res.val;
        }
    }

    // 445

    /**
     * numbers are in right order
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        java.util.Stack<Integer> s1 = new java.util.Stack<Integer>();
        java.util.Stack<Integer> s2 = new java.util.Stack<Integer>();

        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        ;
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }
        ListNode res = new ListNode(0);
        int sum = 0;
        while (!s1.isEmpty() || !s2.isEmpty()) {
            sum /= 10;
            if (!s1.isEmpty()) {
                sum += s1.pop();
            }
            if (!s2.isEmpty()) {
                sum += s2.pop();
            }
            ListNode temp = new ListNode(sum / 10);
            res.val = sum % 10;
            temp.next = res;
            res = temp;
        }
        return res.val == 0 ? res.next : res;
    }
}

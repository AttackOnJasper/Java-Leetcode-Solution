package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class MediumQuestion {
    // 338
    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        for (int i=1; i<=num; i++) res[i] = res[i >> 1] + (i & 1);
        return res;
    }

    // 362
    public class HitCounter {
        private int[] times;
        private int[] hits;
        /** Initialize your data structure here. */
        public HitCounter() {
            times = new int[300];
            hits = new int[300];
        }

        /** Record a hit.
         @param timestamp - The current timestamp (in seconds granularity). */
        public void hit(int timestamp) {
            int index = timestamp % 300;
            if (times[index] != timestamp) {
                times[index] = timestamp;
                hits[index] = 1;
            } else {
                hits[index]++;
            }
        }

        /** Return the number of hits in the past 5 minutes.
         @param timestamp - The current timestamp (in seconds granularity). */
        public int getHits(int timestamp) {
            int total = 0;
            for (int i = 0; i < 300; i++) {
                if (timestamp - times[i] < 300) {
                    total += hits[i];
                }
            }
            return total;
        }
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



    // 370
    public int[] getModifiedArray(int length, int[][] updates) {
        // add val at the start index and -val at the end index
        int res[] = new int[length];
        for (int[] update : updates) {
            res[update[0]] += update[2];
            int end = update[1];
            if(end < length - 1) {
                res[end + 1] -= update[2];
            }
        }
        for (int i = 1; i < length; i++) {
            res[i] += res[i-1];
        }
        return res;
    }
}

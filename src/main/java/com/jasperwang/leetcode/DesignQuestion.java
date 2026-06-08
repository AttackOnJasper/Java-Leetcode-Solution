package com.jasperwang.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Design-focused LeetCode solutions and related utilities.
 *
 * <p>The methods are grouped by problem domain rather than by difficulty. Most implementations are
 * self-contained so they can be copied into individual LeetCode submissions.
 */
public class DesignQuestion {
    /** LeetCode 146: least recently used cache backed by an access-ordered map. */
    public class LRUCache {
        private final int capacity;
        private LinkedHashMap<Integer, Integer> map;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            map =
                    new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
                        protected boolean removeEldestEntry(Map.Entry eldest) {
                            return size() > capacity;
                        }
                    };
        }

        public int get(int key) {
            return map.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            map.put(key, value);
        }
    }

    /**
     * LeetCode 155: stack that supports push, pop, top, and retrieving the minimum in constant
     * time.
     *
     * <p>Tip: push the difference between the current minimum and the value being pushed.
     */
    class MinStack {
        long min;
        final Stack<Long> stack;

        public MinStack() {
            stack = new Stack<>();
        }

        public void push(int x) {
            if (stack.isEmpty()) {
                stack.push(0L);
                min = x;
            } else {
                stack.push(x - min); // need to change to previous min during pop if x is smaller than min
                if (x < min) min = x;
            }
        }

        public void pop() {
            if (stack.isEmpty()) return;
            final long pop = stack.pop();
            if (pop < 0) min = min - pop; // If negative, increase the min value
        }

        public int top() {
            final long top = stack.peek();
            if (top > 0) return (int) (top + min);
            return (int) (min);
        }

        public int getMin() {
            return (int) min;
        }
    }

    /**
     * Push the original min value when min is updated
     */
    class MinStack2 {
        int min = Integer.MAX_VALUE;
        Stack<Integer> stack = new Stack<Integer>();

        public void push(int x) {
            // only push the old minimum value when min would be updated to x
            if (x <= min) {
                stack.push(min);
                min = x;
            }
            stack.push(x);
        }

        public void pop() {
            // if pop operation could result in the changing of the current minimum value,
            // pop twice and change the current minimum value to the last minimum value.
            if (stack.pop() == min) min = stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min;
        }
    }

    /** LeetCode 170: Two Sum data structure. */
    public class TwoSum {
        Set<Integer> num;
        Set<Integer> sum;

        TwoSum() {
            num = new HashSet<Integer>();
            sum = new HashSet<Integer>();
        }

        public void add(int number) {
            if (num.contains(number)) {
                sum.add(number * 2);
            } else {
                num.add(number);
                Iterator<Integer> it = num.iterator();
                while (it.hasNext()) {
                    sum.add(it.next() + number);
                }
            }
        }

        public boolean find(int value) {
            return sum.contains(value);
        }
    }

    /** LeetCode 362: hit counter over a rolling five-minute window. */
    public class HitCounter {
        private int[] times;
        private int[] hits;

        /**
         * Initialize your data structure here.
         */
        public HitCounter() {
            times = new int[300];
            hits = new int[300];
        }

        /**
         * Record a hit.
         *
         * @param timestamp - The current timestamp (in seconds granularity).
         */
        public void hit(int timestamp) {
            int index = timestamp % 300;
            if (times[index] != timestamp) {
                times[index] = timestamp;
                hits[index] = 1;
            } else {
                hits[index]++;
            }
        }

        /**
         * Return the number of hits in the past 5 minutes.
         *
         * @param timestamp - The current timestamp (in seconds granularity).
         */
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

    /** LeetCode 731: calendar that allows double bookings but rejects triple bookings. */
    class MyCalendarTwo {
        private List<int[]> books = new ArrayList<>();

        public boolean book(int s, int e) {
            MyCalendar overlaps = new MyCalendar();
            for (int[] b : books)
                if (Math.max(b[0], s) < Math.min(b[1], e)) // overlap exist
                    if (!overlaps.book(Math.max(b[0], s), Math.min(b[1], e)))
                        return false; // overlaps overlapped
            books.add(new int[]{s, e});
            return true;
        }

        private class MyCalendar {
            List<int[]> books = new ArrayList<>();

            public boolean book(int start, int end) {
                for (int[] b : books) if (Math.max(b[0], start) < Math.min(b[1], end)) return false;
                books.add(new int[]{start, end});
                return true;
            }
        }
    }
}

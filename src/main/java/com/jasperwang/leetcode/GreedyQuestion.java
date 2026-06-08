package com.jasperwang.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Greedy-focused LeetCode solutions and related utilities.
 *
 * <p>The methods are grouped by problem domain rather than by difficulty. Most implementations are
 * self-contained so they can be copied into individual LeetCode submissions.
 */
public class GreedyQuestion {
    /**
     * LeetCode 55: can jump.
     *
     * @param nums input value
     * @return result
     */
    public boolean canJump(int[] nums) {
        int i = 0;
        for (int reach = 0; i < nums.length && i <= reach; ++i) reach = Math.max(i + nums[i], reach);
        return i == nums.length;
    }

    /** LeetCode 435: interval model for erasing overlap intervals. */
    private class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    private class IntervalComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval a, Interval b) {
            return a.end - b.end;
        }
    }

    /**
     * erase overlap intervals.
     *
     * @param intervals input value
     * @return result
     */
    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, new IntervalComparator());
        int numOfIntervalsNotOverlapping = 1;
        for (int i = 1, end = intervals[0].end; i < intervals.length; i++) {
            if (intervals[i].start >= end) {
                end = intervals[i].end;
                numOfIntervalsNotOverlapping++;
            }
        }
        return intervals.length - numOfIntervalsNotOverlapping;
    }

    /**
     * LeetCode 455: cookie.
     *
     * @param g input value
     * @param s input value
     * @return result
     */
    public int findContentChildren(int[] g, int[] s) {
        if (s.length == 0 || g.length == 0) return 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0;
        for (int j = 0; i < g.length && j < s.length; j++) if (g[i] <= s[j]) i++;
        return i;
    }
}

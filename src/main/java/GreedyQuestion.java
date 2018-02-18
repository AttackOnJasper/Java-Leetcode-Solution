package main.java;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by jasperwang on 2018-02-18.
 */
public class GreedyQuestion {
    // 55
    public boolean canJump(int[] nums) {
        int i = 0;
        for (int reach = 0; i < nums.length && i <= reach; ++i)
            reach = Math.max(i + nums[i], reach);
        return i == nums.length;
    }

    // 435
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
}

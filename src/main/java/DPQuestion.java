package main.java;

import java.util.*;

public class DPQuestion {
    private class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

    // weighted interval scheduling: schedule meetings s.t. total weight is maximized
    public int weightedIntervalScheduling(Interval[] intervals, int[] weights) {
        Arrays.sort(intervals, (a, b) -> a.end - b.end);
        int[] lastFinishedIndexes = new int[intervals.length], dp = new int[intervals.length];
        // get the last interval that finishes before the interval
        lastFinishedIndexes[0] = -1;
        for (int i = 1; i < intervals.length; i++) {
            int start = intervals[i].start, low = 0, high = i;
            while (low < high) {
                int mid = (low + high) / 2;
                if (intervals[mid].end == start) {
                    low = mid;
                    break;
                } else if (intervals[mid].end > start) {
                    high = mid;
                } else {
                    low = mid - 1;
                }
            }
            lastFinishedIndexes[i] = intervals[low].end <= start ? low : -1;
        }
        // compute max weights
        dp[0] = weights[0];
        for (int i = 1; i < intervals.length; i++) {
            dp[i] = Math.max(dp[i - 1], lastFinishedIndexes[i] == -1 ? weights[i] : weights[i] + dp[lastFinishedIndexes[i]]);
        }
        return dp[intervals.length - 1];
    }

    // 5. Longest Palindromic Substring
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        boolean[][] dp = new boolean[s.length()][s.length()];
        char[] w = s.toCharArray();
        int maxLen = 0;
        String maxSub = null;

        dp[s.length() - 1][s.length() - 1] = true;
        for (int i = s.length() - 2; i >= 0; --i) {
            int maxJ = i;
            for (int j = i + 1; j < s.length(); j++) {
                if (w[j] == w[i] && (j < i + 3 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    maxJ = j;
                } else {
                    dp[i][j] = false;
                }
            }
            if (maxJ - i + 1 > maxLen) {
                maxLen = maxJ - i + 1;
                maxSub = s.substring(i, maxJ + 1);
            }
        }
        return maxSub;
    }

    // 53 Max subarray
    /** DP dp[i]: max subarray that ends with nums[i] */
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = nums[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
            res = Math.max(res, dp[i]);
        }
        return res;
    }
    // another dp
    public int maxSubArray1(int[] nums) {
        int curMax = nums[0], maxSoFar = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curMax = nums[i] + (curMax > 0 ? curMax : 0);
            maxSoFar = Math.max(curMax, maxSoFar);
        }
        return maxSoFar;
    }
    // Greedy
    /**
     * find the largest difference between the sums when summing up the array from left to right
     */
    public int maxSubArray3(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int sum = 0, min = 0, res = nums[0];
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum - min > res) {
                res = sum - min;
            }
            if (sum < min) {
                min = sum;
            }
        }
        return res;
    }

    // 120. Triangle
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] A = new int[triangle.size()+1];
        for(int i=triangle.size()-1;i>=0;i--){
            for(int j=0;j<triangle.get(i).size();j++){
                A[j] = Math.min(A[j],A[j+1])+triangle.get(i).get(j);
            }
        }
        return A[0];
    }

    // 121 Best Time to buy & sell
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int[] d = new int[prices.length];
        int min = prices[0];
        d[0] = 0;
        for (int i = 1; i < prices.length; i++) {
            if (min >= prices[i]) {
                min = prices[i];
            }
            d[i] = Math.max(d[i - 1], prices[i] - min);
        }
        return d[prices.length - 1];
    }
    /**
     * Kadane's Algorithm: the logic is to calculate the difference (maxCur += prices[i] - prices[i-1])
     * of the original array, and find a contiguous subarray giving maximum profit.
     * If the difference falls below 0, reset it to zero.
     */
    public int maxProfit2(int[] prices) {
        int maxCur = 0, maxSoFar = 0;
        for (int i = 1; i < prices.length; i++) {
            maxCur = Math.max(0, maxCur + prices[i] - prices[i - 1]);
            maxSoFar = Math.max(maxCur, maxSoFar);
        }
        return maxSoFar;
    }

    // 139
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] d = new boolean[s.length()];
        Arrays.fill(d, false);
        d[0] = wordDict.contains(String.valueOf(s.charAt(0)));
        for (int i = 1; i < d.length; i++) {
            if (wordDict.contains(s.substring(0, i + 1))) {
                d[i] = true;
            } else {
                for (int j = 0; j < i; j++) {
                    if (d[j] && wordDict.contains(s.substring(j + 1, i + 1))) {
                        d[i] = true;
                    }
                }
            }
        }
        return d[d.length - 1];
    }

    // 221. Maximal Square: Given a 2D binary matrix filled with 0's and 1's, find the largest
    // square containing only 1's and return its area.
    public int maximalSquare(char[][] matrix) {
        if(matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length, result = 0;
        int[][] b = new int[m+1][n+1];
        for (int i = 1 ; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if(matrix[i-1][j-1] == '1') {
                    b[i][j] = Math.min(Math.min(b[i][j-1] , b[i-1][j-1]), b[i-1][j]) + 1;
                    result = Math.max(b[i][j], result); // update result
                }
            }
        }
        return result*result;
    }

    // 253. Meeting Rooms II. find the minimum meeting rooms required to fulfill all meetings
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, (a, b) -> a.start - b.start);
        PriorityQueue<Interval> heap = new PriorityQueue<>(intervals.length, (a, b) -> a.end - b.end); // track minimum end time of merged intervals
        heap.offer(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            Interval interval = heap.poll();
            if (intervals[i].start >= interval.end) {   // do not need a new room; merge 2 intervals into a larger one
                interval.end = intervals[i].end;
            } else {  // 2 meetings overlap; need a new room
                heap.offer(intervals[i]);
            }
            heap.offer(interval);  // push the merged interval back to the priority queue
        }
        return heap.size();
    }

    // 256. Paint House: each house can be painted from one of the three colours & each adjacent
    // house cannot share the same colour
    /** reduction of space complexity by keeping only prev & curr values */
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0) return 0;
        int n = costs.length, r = 0, g = 0, b = 0;
        for (int i = 0; i < n; i++) {
            int rr = r, bb = b, gg = g;
            r = costs[i][0] + Math.min(bb, gg);
            b = costs[i][1] + Math.min(rr, gg);
            g = costs[i][2] + Math.min(rr, bb);
        }
        return Math.min(r, Math.min(g, b));
    }

    // 276 Paint Fence
    /**
     * Return the total number of ways to paint n posts given k colors s.t. no more than 2 adjacent
     * posts have the same color
     */
    public int numWays(int n, int k) {
        if (n == 0) return 0;
        if (n == 1) return k;
        int diffColorCounts = k * (k - 1);
        int sameColorCounts = k;
        for (int i = 2; i < n; i++) {
            int temp = diffColorCounts;
            diffColorCounts = (diffColorCounts + sameColorCounts) * (k - 1);
            sameColorCounts = temp;
        }
        return diffColorCounts + sameColorCounts;
    }

    // 516 Longest Palindrome Subsequence
    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];

        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][s.length() - 1];
    }

    // 416 Partition Equal Subset Sum
    /** knapsack */
    public boolean canPartition(int[] nums) {
        int sum = 0;

        for (int num : nums) {
            sum += num;
        }

        if ((sum & 1) == 1) {
            return false;
        }
        sum /= 2;

        int n = nums.length;
        boolean[] dp = new boolean[sum+1];
        Arrays.fill(dp, false);
        dp[0] = true;

        for (int num : nums) {
            for (int i = sum; i > 0; i--) {
                if (i >= num) {
                    dp[i] = dp[i] || dp[i-num];
                }
            }
        }

        return dp[sum];
    }

    // 712 find the lowest ASCII sum of deleted characters to make two strings equal
    /** similar to edit distance */
    public static int minimumDeleteSum(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        /**
         * dp[i][j]: the minimum delete sum for s1[0 - i] & s2[0 - j]
         */
        int[][] dp = new int[m + 1][n + 1];
        for (int j = 1; j <= n; j++) dp[0][j] = dp[0][j-1]+ s2.charAt(j-1);
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i-1][0]+ s1.charAt(i-1);
            for (int j = 1; j <= n; j++)
                dp[i][j] = s1.charAt(i-1) == s2.charAt(j-1)? dp[i-1][j-1] :
                    Math.min(dp[i-1][j] + s1.charAt(i-1), dp[i][j-1] + s2.charAt(j-1));
        }
        return dp[m][n];
    }

    // 714 Best Time to Buy and Sell Stock with Transaction Fee
    /**
     * notHoldStock represents the max profit of not holding a stock at price p
     * holdStock represents the max profit of holding a stock at price p
     * Update notHoldStock by selling the stock (bought at max profit while holding a stock i.e. holdStock) at price p, so profit = holdStock + p
     * Update holdStock by buying the stock at p
     * */
    public static int maxProfit(int[] prices, int fee) {
        int notHoldStock = 0, holdStock = Integer.MIN_VALUE;
        for (int p : prices) {
            int tmp = notHoldStock;
            notHoldStock = Math.max(notHoldStock, holdStock + p);
            holdStock = Math.max(holdStock, tmp - p - fee);
        }
        return notHoldStock;
    }

    // 717
    public static boolean isOneBitCharacter(int[] bits) {
        boolean[] isValid = new boolean[bits.length];
        isValid[0] = bits[0] == 0;
        for (int i = 1; i < bits.length; i++) {
            isValid[i] = (bits[i] == 0 && isValid[i-1]) || (bits[i-1] == 1 && (i > 1 ? isValid[i-2] : true));
        }
        return bits.length > 1 ? isValid[bits.length - 2] : isValid[bits.length - 1];
    }

    // 718 find the length of longest subarray in both arrays
    public static int findLength(int[] A, int[] B) {
        int res = 0;
        int[][] dp = new int[A.length][B.length]; // store the length for max subarray ending with i & j
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                dp[i][j] = A[i] == B[j] ? 1 + (i > 0 && j > 0 ? dp[i-1][j-1] : 0) : 0;
                res = Integer.max(dp[i][j], res);
            }
        }
        return res;
    }

    // 740
    // The length of nums is at most 20000. Each element nums[i] is an integer in the range [1, 10000]
    public static int deleteAndEarn(int[] nums) {
        final int[] values = new int[10001];  // values array stores sums of buckets
        for (int num : nums) {
            values[num] += num;
        }
        int take = 0, skip = 0;
        for (final int value : values) {
            final int temp = Math.max(skip + value, take);
            skip = take;
            take = temp;
        }
        return take;
    }
}

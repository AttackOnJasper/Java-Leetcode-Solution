package com.jasperwang.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * DP-focused LeetCode solutions and related utilities.
 *
 * <p>The methods are grouped by problem domain rather than by difficulty. Most implementations are
 * self-contained so they can be copied into individual LeetCode submissions.
 */
public class DPQuestion {

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

    private class WeightedInterval {

        int start;
        int end;
        int weight;

        WeightedInterval(Interval interval, int weight) {
            this.start = interval.start;
            this.end = interval.end;
            this.weight = weight;
        }
    }

    /**
     * Finds the maximum total weight of non-overlapping intervals.
     *
     * <p>{@code intervals[i]} and {@code weights[i]} describe the same job. Two jobs are compatible
     * when the earlier job ends at or before the later job starts.
     *
     * @param intervals jobs with start and end times
     * @param weights   weight or profit for each corresponding job
     * @return maximum total weight obtainable by selecting compatible jobs
     */
    public int weightedIntervalScheduling(Interval[] intervals, int[] weights) {
        if (intervals == null || weights == null || intervals.length == 0) {
            return 0;
        }
        if (intervals.length != weights.length) {
            throw new IllegalArgumentException("intervals and weights must have the same length");
        }

        WeightedInterval[] jobs = new WeightedInterval[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            jobs[i] = new WeightedInterval(intervals[i], weights[i]);
        }
        Arrays.sort(jobs, Comparator.comparingInt(job -> job.end));

        int[] dp = new int[jobs.length];
        dp[0] = jobs[0].weight;
        for (int i = 1; i < intervals.length; i++) {
            int lastCompatible = -1;
            int low = 0, high = i - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (jobs[mid].end <= jobs[i].start) {
                    lastCompatible = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            int include = jobs[i].weight + (lastCompatible == -1 ? 0 : dp[lastCompatible]);
            dp[i] = Math.max(dp[i - 1], include);
        }
        return dp[jobs.length - 1];
    }

    /**
     * Determines whether any subset of the numbers can sum to the target.
     *
     * <p>{@code dp[i][k]} represents whether a subset from indexes {@code 0..i} can sum to
     * {@code k}.
     *
     * @param nums candidate numbers
     * @param k    target subset sum
     * @return {@code true} when at least one subset sums to {@code k}
     */
    public boolean subsetSum(int[] nums, int k) {
        boolean[][] dp = new boolean[nums.length][k + 1];
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = true;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = dp[i - 1][j] || dp[i - 1][k - nums[i]];
            }
        }
        return dp[nums.length - 1][k];
    }

    /**
     * Finds the maximum value that can be packed within a weight limit.
     *
     * <p>{@code weight[i]} and {@code value[i]} describe the same item.
     *
     * @param weight item weights
     * @param value  item values
     * @param limit  maximum total weight allowed
     * @return maximum total value within {@code limit}
     */
    public int knapsack(int[] weight, int[] value, int limit) {
        int[][] dp = new int[weight.length][limit + 1]; // dp[i][w] represents max value from item 0 - i with limit w
        for (int i = 1; i < weight.length; i++) {
            for (int j = weight[i]; j <= limit; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
            }
        }
        return dp[weight.length - 1][limit];
    }

    /**
     * LeetCode 5: finds the longest palindromic substring.
     *
     * @param s source string
     * @return longest contiguous substring of {@code s} that is a palindrome
     */
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

    /**
     * LeetCode 53: finds the maximum subarray sum.
     *
     * <p>{@code dp[i]} stores the best subarray sum ending at {@code nums[i]}.
     *
     * @param nums integer array
     * @return largest sum over all contiguous subarrays
     */
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

    /**
     * Finds the maximum subarray sum using a space-optimized DP variant.
     *
     * @param nums integer array
     * @return largest sum over all contiguous subarrays
     */
    public int maxSubArray1(int[] nums) {
        int curMax = nums[0], maxSoFar = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curMax = nums[i] + (curMax > 0 ? curMax : 0);
            maxSoFar = Math.max(curMax, maxSoFar);
        }
        return maxSoFar;
    }

    /**
     * Finds the maximum subarray sum using prefix sums.
     *
     * <p>Tracks the largest difference between the running sum and the minimum prefix sum seen so
     * far.
     *
     * @param nums integer array
     * @return largest sum over all contiguous subarrays
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

    /**
     * LeetCode 120: finds the minimum path total from top to bottom of a triangle.
     *
     * @param triangle rows of triangle values
     * @return minimum sum along a path from the top row to the bottom row
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] A = new int[triangle.size() + 1];
        for (int i = triangle.size() - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                A[j] = Math.min(A[j], A[j + 1]) + triangle.get(i).get(j);
            }
        }
        return A[0];
    }

    /**
     * LeetCode 121: finds the maximum profit from one stock buy and one sell.
     *
     * <p>Uses Kadane's algorithm over day-to-day price differences, resetting the running profit
     * when it drops below zero.
     *
     * @param prices stock price by day
     * @return maximum profit from one transaction, or {@code 0} when no profitable trade exists
     */
    public int maxProfit(int[] prices) {
        int maxCur = 0, maxSoFar = 0;
        for (int i = 1; i < prices.length; i++) {
            maxCur = Math.max(0, maxCur + prices[i] - prices[i - 1]);
            maxSoFar = Math.max(maxCur, maxSoFar);
        }
        return maxSoFar;
    }

    /**
     * LeetCode 123: finds the maximum stock profit with at most two transactions.
     *
     * @param prices stock price by day
     * @return maximum profit from at most two buy/sell transactions
     */
    public int maxProfit3(int[] prices) {
        int hold0 = Integer.MIN_VALUE, sold0 = 0, hold1 = Integer.MIN_VALUE, sold1 = 0;
        for (int p : prices) {
            sold1 = Math.max(sold1, hold1 + p); // max profit after selling 2nd stock
            hold1 = Math.max(hold1, sold0 - p); // max profit while holding 2nd stock
            sold0 = Math.max(sold0, hold0 + p); // max profit after selling 1st stock
            hold0 = Math.max(hold0, -p); // max profit while holding 1st stock
        }
        return sold1;
    }

    /**
     * LeetCode 139: determines whether a string can be segmented into dictionary words.
     *
     * @param s        source string
     * @param wordDict dictionary of valid words
     * @return {@code true} when {@code s} can be fully segmented using {@code wordDict}
     */
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

    /**
     * LeetCode 198: finds the maximum money that can be robbed without robbing adjacent houses.
     *
     * @param nums money available in each house
     * @return maximum amount obtainable without taking two adjacent houses
     */
    public int rob(int[] nums) {
        int robbedPrev = 0, notRobbedPrev = 0;
        for (int n : nums) {
            int robbedCur = notRobbedPrev + n;
            int notRobbedCur = Math.max(notRobbedPrev, robbedPrev);
            robbedPrev = robbedCur;
            notRobbedPrev = notRobbedCur;
        }
        return Math.max(robbedPrev, notRobbedPrev);
    }

    /**
     * LeetCode 221: finds the area of the largest square containing only {@code '1'} cells.
     *
     * @param matrix binary matrix represented by {@code '0'} and {@code '1'} characters
     * @return area of the largest all-ones square
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int m = matrix.length, n = matrix[0].length, result = 0;
        int[][] b = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    b[i][j] = Math.min(Math.min(b[i][j - 1], b[i - 1][j - 1]), b[i - 1][j]) + 1;
                    result = Math.max(b[i][j], result); // update result
                }
            }
        }
        return result * result;
    }

    /**
     * LeetCode 253: finds the minimum number of meeting rooms required.
     *
     * @param intervals meeting intervals with start and end times
     * @return minimum number of rooms needed so all meetings can be scheduled
     */
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (a, b) -> a.start - b.start);
        PriorityQueue<Interval> heap =
            new PriorityQueue<>(
                intervals.length,
                (a, b) -> a.end - b.end); // track minimum end time of merged intervals
        heap.offer(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            Interval interval = heap.poll();
            if (intervals[i].start
                >= interval.end) { // do not need a new room; merge 2 intervals into a larger one
                interval.end = intervals[i].end;
            } else { // 2 meetings overlap; need a new room
                heap.offer(intervals[i]);
            }
            heap.offer(interval); // push the merged interval back to the priority queue
        }
        return heap.size();
    }

    /**
     * LeetCode 256: finds the minimum cost to paint houses with no adjacent houses sharing a color.
     *
     * <p>{@code costs[i][j]} is the cost of painting house {@code i} with color {@code j}. The
     * implementation keeps only the previous and current color totals.
     *
     * @param costs painting costs for each house and color
     * @return minimum total painting cost
     */
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }
        int n = costs.length, r = 0, g = 0, b = 0;
        for (int i = 0; i < n; i++) {
            int rr = r, bb = b, gg = g;
            r = costs[i][0] + Math.min(bb, gg);
            b = costs[i][1] + Math.min(rr, gg);
            g = costs[i][2] + Math.min(rr, bb);
        }
        return Math.min(r, Math.min(g, b));
    }

    /**
     * LeetCode 276: counts ways to paint a fence with no more than two adjacent posts sharing a color.
     *
     * @param n number of fence posts
     * @param k number of available colors
     * @return number of valid painting arrangements
     */
    public int numWays(int n, int k) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return k;
        }
        int diffColorCounts = k * (k - 1);
        int sameColorCounts = k;
        for (int i = 2; i < n; i++) {
            int temp = diffColorCounts;
            diffColorCounts = (diffColorCounts + sameColorCounts) * (k - 1);
            sameColorCounts = temp;
        }
        return diffColorCounts + sameColorCounts;
    }

    /**
     * LeetCode 300: finds the length of the longest increasing subsequence.
     *
     * @param nums integer array
     * @return length of the longest strictly increasing subsequence
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
        }
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = 0;
        for (int n : dp) {
            res = Math.max(n, res);
        }
        return res;
    }

    /**
     * LeetCode 338: counts set bits for every number from {@code 0} to {@code num}.
     *
     * @param num inclusive upper bound
     * @return array where index {@code i} contains the number of set bits in {@code i}
     */
    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            res[i] = res[i / 2] + i % 2;
        }
        return res;
    }

    /**
     * LeetCode 516: finds the length of the longest palindromic subsequence.
     *
     * @param s source string
     * @return length of the longest subsequence of {@code s} that is a palindrome
     */
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

    /**
     * LeetCode 416: determines whether the array can be partitioned into two equal-sum subsets.
     *
     * <p>Uses a knapsack-style DP over half of the total sum.
     *
     * @param nums positive integers to partition
     * @return {@code true} when some subset sums to half of the total
     */
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
        boolean[] dp = new boolean[sum + 1];
        Arrays.fill(dp, false);
        dp[0] = true;

        for (int num : nums) {
            for (int i = sum; i > 0; i--) {
                if (i >= num) {
                    dp[i] = dp[i] || dp[i - num];
                }
            }
        }

        return dp[sum];
    }

    /**
     * LeetCode 486: predicts whether player one can win when both players play optimally.
     *
     * <p>{@code dp[i][j]} stores how much more score player one can obtain than player two from
     * subarray {@code i..j}.
     *
     * @param nums score values available from either end
     * @return {@code true} when player one can win or tie
     */
    public boolean PredictTheWinner(int[] nums) {
        int[][] dp = new int[nums.length][nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i][i] = nums[i];
        }
        for (int len = 1; len < nums.length; len++) {
            for (int i = 0; i < nums.length - len; i++) {
                int j = i + len;
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][nums.length - 1] >= 0;
    }

    /*
     * LeetCode 646.
     *
     * Tip: similar to longest increasing sequence.
     */

    /**
     * LeetCode 712: finds the minimum ASCII delete sum needed to make two strings equal.
     *
     * <p>Tip: this is similar to edit distance, but each deletion costs the ASCII value of the
     * deleted character.
     *
     * @param s1 first string
     * @param s2 second string
     * @return minimum total ASCII value of deleted characters
     */
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        /**
         * dp[i][j]: the minimum delete sum for s1[0 - i] & s2[0 - j], it has 1 extra length to account
         * for delete sum from one string to null
         */
        int[][] dp = new int[m + 1][n + 1];
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] + s2.charAt(j - 1);
        }
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                dp[i][j] =
                    s1.charAt(i - 1) == s2.charAt(j - 1)
                        ? dp[i - 1][j - 1]
                        : Math.min(dp[i - 1][j] + s1.charAt(i - 1), dp[i][j - 1] + s2.charAt(j - 1));
            }
        }
        return dp[m][n];
    }

    /**
     * LeetCode 714: finds the maximum stock profit with a transaction fee.
     *
     * <p>{@code notHoldStock} tracks the best profit while not holding a stock, and
     * {@code holdStock} tracks the best profit while holding one.
     *
     * @param prices stock price by day
     * @param fee    transaction fee paid when buying in this implementation
     * @return maximum profit after any number of transactions
     */
    public int maxProfit(int[] prices, int fee) {
        int notHoldStock = 0, holdStock = Integer.MIN_VALUE;
        for (int p : prices) {
            int tmp = notHoldStock;
            notHoldStock = Math.max(notHoldStock, holdStock + p);
            holdStock = Math.max(holdStock, tmp - p - fee);
        }
        return notHoldStock;
    }

    /**
     * LeetCode 717: determines whether the final character is a one-bit character.
     *
     * @param bits encoded bit sequence ending with {@code 0}
     * @return {@code true} when the final character is decoded as a one-bit character
     */
    public static boolean isOneBitCharacter(int[] bits) {
        boolean[] isValid = new boolean[bits.length];
        isValid[0] = bits[0] == 0;
        for (int i = 1; i < bits.length; i++) {
            isValid[i] =
                (bits[i] == 0 && isValid[i - 1]) || (bits[i - 1] == 1 && (i > 1 ? isValid[i - 2] : true));
        }
        return bits.length > 1 ? isValid[bits.length - 2] : isValid[bits.length - 1];
    }

    /**
     * LeetCode 718: finds the length of the longest repeated subarray shared by two arrays.
     *
     * @param A first integer array
     * @param B second integer array
     * @return maximum length of a contiguous subarray appearing in both arrays
     */
    public int findLength(int[] A, int[] B) {
        int res = 0;
        int[][] dp = new int[A.length][B.length]; // store the length for max subarray ending with i & j
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                dp[i][j] = A[i] == B[j] ? 1 + (i > 0 && j > 0 ? dp[i - 1][j - 1] : 0) : 0;
                res = Integer.max(dp[i][j], res);
            }
        }
        return res;
    }

    /**
     * LeetCode 740: finds the maximum points obtainable by deleting numbers and their neighbors.
     *
     * <p>The implementation buckets values in the range {@code [1, 10000]} and applies the House
     * Robber recurrence over those buckets.
     *
     * @param nums numbers available to delete and earn
     * @return maximum points obtainable
     */
    public int deleteAndEarn(int[] nums) {
        final int[] values = new int[10001]; // values array stores sums of buckets
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

    /**
     * LeetCode 357: counts numbers with unique digits for the range {@code 0 <= x < 10^n}.
     *
     * <p>Example: {@code n = 2} returns {@code 91}.
     *
     * @param n number of digits in the upper-bound power of ten
     * @return count of numbers with no repeated digits
     */
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        int res = 10;
        int uniqueDigits = 9;
        int availableNumber = 9;
        while (n-- > 1 && availableNumber > 0) {
            uniqueDigits = uniqueDigits * availableNumber;
            res += uniqueDigits;
            availableNumber--;
        }
        return res;
    }

    /**
     * LeetCode 779: returns the {@code K}th symbol in grammar row {@code N}.
     *
     * @param N grammar row number, one-indexed
     * @param K symbol position within the row, one-indexed
     * @return value of the requested symbol, either {@code 0} or {@code 1}
     */
    public int kthGrammar(int N, int K) {
        if (N == 1) {
            return 0;
        }
        if (K % 2 == 0) {
            return (kthGrammar(N - 1, K / 2) == 0) ? 1 : 0;
        }
        return (kthGrammar(N - 1, (K + 1) / 2) == 0) ? 0 : 1;
    }
}

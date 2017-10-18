package main;

import java.util.*;

public class DPQuestion {
    // 5. Longest Palindromic Substring
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) return s;
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
    // DP dp[i]: max subarray that ends with nums[i]
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

    // 256. Paint House
    public int minCost(int[][] costs) {
        int n = costs.length;
        if (n == 0) {
            return 0;
        }
        int[][] d = new int[n][3];
        d[0] = costs[0];
        for (int i = 1; i < n; i++) {
            d[i][0] = costs[i][0] + Math.min(d[i - 1][1], d[i - 1][2]);
            d[i][1] = costs[i][1] + Math.min(d[i - 1][0], d[i - 1][2]);
            d[i][2] = costs[i][2] + Math.min(d[i - 1][0], d[i - 1][1]);
        }
        return Math.min(d[n - 1][0], Math.min(d[n - 1][1], d[n - 1][2]));
    }

    // 276 Faint Fence
    /**
     * Return the total number of ways to paint n posts given k colors s.t. no more than 2 adjacent
     * posts have the same color
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
}

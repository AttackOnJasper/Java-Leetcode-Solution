package main;

import java.util.*;

public class DPQuestion {
    // 5. Longest Palindromic Substring
    public String longestPalindrome(String s) {
        if(s==null || s.length() <= 1) return s;

        boolean[][] dp = new boolean[s.length()][s.length()];
        char[] w = s.toCharArray();
        int maxLen = 0;
        String maxSub = null;

        dp[s.length()-1][s.length()-1] = true;
        for(int i = s.length()-2;i>=0;--i){
            int maxJ = i;
            for (int j = i+1; j < s.length(); j++) {
                if(w[j] == w[i] && (j<i+3 || dp[i+1][j-1])){
                    dp[i][j] = true;
                    maxJ = j;
                }else{
                    dp[i][j] = false;
                }
            }

            if(maxJ - i+1 > maxLen){
                maxLen = maxJ - i+1;
                maxSub = s.substring(i,maxJ+1);
            }
        }
        return maxSub;
    }

    // 120. Triangle


    // 256. Paint House
    public int minCost(int[][] costs) {
        int n = costs.length;
        if (n == 0) return 0;
        int[][] d = new int[n][3];
        d[0] = costs[0];
        for (int i = 1; i < n; i++) {
            d[i][0] = costs[i][0] + Math.min(d[i-1][1], d[i-1][2]);
            d[i][1] = costs[i][1] + Math.min(d[i-1][0], d[i-1][2]);
            d[i][2] = costs[i][2] + Math.min(d[i-1][0], d[i-1][1]);
        }
        return Math.min(d[n-1][0], Math.min(d[n-1][1], d[n-1][2]));
    }

    // 276 Faint Fence
    /** Return the total number of ways to paint n posts given k colors s.t. no more than 2 adjacent
     * posts have the same color
     * */
    public int numWays(int n, int k) {
        if(n == 0) return 0;
        if(n == 1) return k;
        int diffColorCounts = k*(k-1);
        int sameColorCounts = k;
        for(int i=2; i<n; i++) {
            int temp = diffColorCounts;
            diffColorCounts = (diffColorCounts + sameColorCounts) * (k-1);
            sameColorCounts = temp;
        }
        return diffColorCounts + sameColorCounts;
    }

    // 516
    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];

        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i+1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][s.length()-1];
    }
    
}

package main;

/**
 * Created by jasperwang on 2017-09-21.
 */
public class StringQuestion {
    // 680
    public boolean validPalindrome(String s) {
        int i = -1, j = s.length();
        while (++i < --j) {
            if (s.charAt(i) != s.charAt(j)) {
                // either remove i or remove j
                return isPalindromic(s, i, j+1) || isPalindromic(s, i-1, j);
            }
        }
        return true;
    }

    public boolean isPalindromic(String s, int l, int r) {
        while (++l < --r)
            if (s.charAt(l) != s.charAt(r)) return false;
        return true;
    }
}

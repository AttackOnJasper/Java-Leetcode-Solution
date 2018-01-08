package main.java;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class StringQuestion {
    // 5 Longest Palindrome Substring
    private int lo, maxLen;
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2)
            return s;

        for (int i = 0; i < len-1; i++) {
            longestPalindromeHelper(s, i, i);  //assume odd length, try to extend Palindrome as possible
            longestPalindromeHelper(s, i, i+1); //assume even length.
        }
        return s.substring(lo, lo + maxLen);
    }
    private void longestPalindromeHelper(String s, int j, int k) {
        while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
            j--;
            k++;
        }
        if (maxLen < k - j - 1) {
            lo = j + 1;
            maxLen = k - j - 1;
        }
    }

    // Cipher
    public static String cipherEncode(final String text, final int shift) {
        final StringBuilder sb = new StringBuilder();
        final char[] arr = text.toCharArray();
        for (final char c : arr) {
            if (Character.isUpperCase(c)) {
                sb.append((char) ((c + shift - 65) % 26 + 65));
            } else if (Character.isLowerCase(c)) {
                sb.append((char) ((c + shift - 97) % 26 + 97));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String cipherDecode(final String text, final int shift) {
        final StringBuilder sb = new StringBuilder();
        final char[] arr = text.toCharArray();
        for (final char c : arr) {
            if (Character.isUpperCase(c)) {
                sb.append((char) (c - shift - 65 >= 0 ? c - shift : c - shift + 26));
            } else if (Character.isLowerCase(c)) {
                sb.append((char) (c - shift - 97 >= 0 ? c - shift : c - shift + 26));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    // 14
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        StringBuilder result = new StringBuilder();
        Arrays.sort(strs);
        char [] a = strs[0].toCharArray();
        char [] b = strs[strs.length-1].toCharArray();

        for (int i = 0; i < a.length; i ++){
            if (b.length > i && b[i] == a[i]){
                result.append(b[i]);
            } else {
                return result.toString();
            }
        }
        return result.toString();
    }

    // 67
    public String addBinary(String a, String b) {
        String res = "";
        int i = a.length() - 1, j = b.length() - 1, c = 0;
        while (i >= 0 || j >= 0 || c == 1) {
            int temp = (i >= 0 ? (a.charAt(i--) - '0') : 0) + (j >= 0 ? (b.charAt(j--) - '0') : 0) + c;
            res = temp % 2 + res;
            c = temp / 2;
        }
        return res;
    }

    // 205 Isomorphic Strings (Yext)
    public boolean isIsomorphic(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        if (sLen != tLen) return false;
        int[] cs = new int[128], ct = new int[128];
        for (int i = 0; i < sLen; i++) {
            /** check if the first index of corresponding char matches */
            if (cs[s.charAt(i)] != ct[t.charAt(i)]) return false;
            if (cs[s.charAt(i)] == 0) {
                cs[s.charAt(i)] = i + 1;  // prevents 0
                ct[t.charAt(i)] = i + 1;
            }
        }
        return true;
    }

    // 459 Repeated Substring pattern
    public boolean repeatedSubstringPattern(String s) {
        final int length = s.length();
        for (int i = length / 2; i >= 1; i--) {
            if (length % i == 0) {
                final int m = length / i;
                final String subS = s.substring(0, i);
                int j;
                for (j = 1; j < m; j++) {
                    if (!subS.equals(s.substring(j * i, i + j * i))) {
                        break;
                    }
                }
                if (j == m) {
                    return true;
                }
            }
        }
        return false;
    }

    // 293 Flip game: replace two "++" with "--"
    public List<String> generatePossibleNextMoves(String s) {
        List<String> res = new LinkedList<>();
        for (int i=-1; (i = s.indexOf("++", i+1)) >= 0; ) {
            res.add(s.substring(0, i) + "--" + s.substring(i+2));
        }
        return res;
    }

    // 647 Palindrome substring: return # of palindromes in string
    int count = 0;
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        for (int i = 0; i < s.length(); i++) { // i is the mid point
            extendPalindrome(s, i, i); // odd length;
            extendPalindrome(s, i, i + 1); // even length
        }
        return count;
    }
    private void extendPalindrome(String s, int left, int right) {
        while (left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++; left--; right++;
        }
    }

    // 680 Palindrome 2: can have one character difference
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
    private boolean isPalindromic(String s, int l, int r) {
        while (++l < --r)
            if (s.charAt(l) != s.charAt(r)) return false;
        return true;
    }

    // 681. Next Closest Time
    public String nextClosestTime(String time) {
        return "";
    }
}

package main.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    // 20 Valid Parenthesis
    public boolean isValid(String s) {
        char[] arr = s.toCharArray();
        Stack<Character> stack = new Stack<Character>();
        for (char c : arr) {
            switch (c) {
                case '(':
                case '[':
                case '{':
                    stack.push(c);
                    break;
                case ')':
                    if (stack.isEmpty() || stack.pop() != '(') return false;
                    break;
                case ']':
                    if (stack.isEmpty() || stack.pop() != '[') return false;
                    break;
                case '}':
                    if (stack.isEmpty() || stack.pop() != '{') return false;
                    break;
            }
        }
        return stack.isEmpty();
    }

    // 28 IndexOf
    public int strStr(String haystack, String needle) {
        for (int i = 0; ; i++) {
            for (int j = 0; ; j++) {
                if (j == needle.length()) return i;
                if (i + j == haystack.length()) return -1;
                if (needle.charAt(j) != haystack.charAt(i + j)) break;
            }
        }
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

    // 125
    public static boolean isPalindrome(String s) {
        String temp = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String reversed = new StringBuffer(temp).reverse().toString();
        return reversed.equals(temp);
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

    // 266 return true if permutation of string can be a palindrome
    public boolean canPermutePalindrome(String s) {
        HashSet<Character> h = new HashSet<Character>();
        char[] arr = s.toCharArray();
        for (char c : arr) {
            if (h.contains(c)) {
                h.remove(c);
            } else {
                h.add(c);
            }
        }
        return h.size() < 2;
    }

    // 290 Word Pattern
    public boolean wordPattern(String pattern, String str) {
        HashMap<Character, String> map = new HashMap<Character, String>();
        String[] splited = str.split(" ");
        char[] arr = pattern.toCharArray();
        if (arr.length != splited.length) {
            return false;
        }
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                if (!map.get(arr[i]).equals(splited[i])) {
                    return false;
                }
            } else {
                if (map.containsValue(splited[i])) {
                    return false;
                }
                map.put(arr[i], splited[i]);
            }
        }
        return true;
    }

    // 293 Flip game: replace two "++" with "--"
    public List<String> generatePossibleNextMoves(String s) {
        List<String> res = new LinkedList<>();
        for (int i=-1; (i = s.indexOf("++", i+1)) >= 0; ) {
            res.add(s.substring(0, i) + "--" + s.substring(i+2));
        }
        return res;
    }

    // 345 Reverse Vowels
    /** two pointer */

    // 389 Find the Difference
    public char findTheDifference(String s, String t) {
        s += t;
        char[] arr = s.toCharArray();
        char res = 0;
        for (char c : arr) {
            res ^= c;
        }
        return res;
    }

    // 415 Add two Strings
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        for (int i = num1.length() - 1, j = num2.length() - 1, carry = 0 ; i >= 0 || j >= 0 || carry == 1; i++, j++) {
            int d1 = i < 0? 0 : num1.charAt(i) - '0';
            int d2 = j < 0? 0 : num2.charAt(j) - '0';
            sb.append((d1 + d2 + carry) % 10);
            carry = (d1 + d2 + carry) / 10;
        }
        return sb.reverse().toString();
    }

    // 434
    /** " a b c" split -> "" "a" "b" "c" */
    public int countSegments(String s) {
        return ("x " + s).split(" +").length - 1;
    }

    // 438
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0) return list;
        int[] hash = new int[256]; //character hash
        //record each character in p to hash
        for (char c : p.toCharArray()) {
            hash[c]++;
        }
        //two points, initialize count to p's length
        int left = 0, right = 0, count = p.length();
        while (right < s.length()) {
            //move right everytime, if the character exists in p's hash, decrease the count
            //current hash value >= 1 means the character is existing in p
            if (hash[s.charAt(right++)]-- >= 1) count--;

            //when the count is down to 0, means we found the right anagram
            //then add window's left to result list
            if (count == 0) list.add(left);

            //if we find the window's size equals to p, then we have to move left (narrow the window) to find the new match window
            //++ to reset the hash because we kicked out the left
            //only increase the count if the character is in p
            //the count >= 0 indicate it was original in the hash, cuz it won't go below 0
            if (right - left == p.length() && hash[s.charAt(left++)]++ >= 0) count++;
        }
        return list;
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
    // Given a time "HH:MM", form the next closest time by re-using the current digits. No limit on number of times a digit is re-used
    public String nextClosestTime(String time) {
        int[] arr = new int[]{time.charAt(0) - '0', time.charAt(1) - '0', time.charAt(3) - '0', time.charAt(4) - '0'};
        Set<Integer> set = IntStream.of(arr).boxed().collect(Collectors.toSet());
        int curTime = 60 * Integer.parseInt(time.substring(0, 2)) + Integer.parseInt(time.substring(3, 5));
        while (true) {
            curTime = (curTime + 1) % 1440;
            int[] digits = new int[]{curTime / 60 / 10, curTime / 60 % 10, curTime % 60 / 10, curTime % 60 % 10};
            for (int i = 0; ; i++) {
                if (!set.contains(digits[i])) break;
                if (i == digits.length - 1) return String.format("%02d:%02d", curTime / 60, curTime % 60);
            }
        }
    }

    // 681. Next Closest Time variation
    // Given a time "HH:MM", form the next closest time by re-using the current digits. each digit can be used once
    public String nextClosestTimeII(String S) {
        int minute = Integer.parseInt(S.substring(3, 5)), hour = Integer.parseInt(S.substring(0, 2));
        int[] arr = new int[]{hour / 10, hour % 10, minute / 10, minute % 10};
        HashMap<Integer, Integer> originalTimeMap = new HashMap<>();
        for (int n : arr) {
            originalTimeMap.put(n, originalTimeMap.getOrDefault(n, 0) + 1);
        }
        final int totalTime = 24 * 60;
        int curTime = 60 * hour + minute;
        while (true) {
            curTime = (curTime + 1) % totalTime;
            int[] digits = new int[]{curTime / 60 / 10, curTime / 60 % 10, curTime % 60 / 10, curTime % 60 % 10};
            HashMap<Integer, Integer> curTimeMap = new HashMap<>();
            for (int d : digits) {
                if (!originalTimeMap.containsKey(d)) break;
                curTimeMap.put(d, curTimeMap.getOrDefault(d, 0) + 1);
            }
            // test permutation
            boolean isValidRes = true;
            for (int key : originalTimeMap.keySet()) {
                if (originalTimeMap.get(key) != curTimeMap.get(key)) {
                    isValidRes = false;
                    break;
                }
            }
            if (isValidRes) return String.format("%02d:%02d", curTime / 60, curTime % 60);
        }
    }

    // 696: Give a string s, count the number of non-empty (contiguous) substrings that have the
    // same number of 0's and 1's, and all the 0's and all the 1's in these substrings are grouped consecutively.
    public int countBinarySubstrings(String s) {
        char[] arr = s.toCharArray();
        int res = 0, prev = 0, curr = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i-1]) {
                curr++;
            } else {
                prev = curr;
                curr = 1;
            }
            if (prev >= curr) res++;
        }
        return res;
    }

    // 767. Reorganize string
    public String reorganizeString(String S) {
        char[] temp = S.toCharArray();
        Arrays.sort(temp);
        int cur = 1, max = 0;
        for (int i = 1; i < temp.length; i++) {
            if (temp[i] != temp[i-1]) {
                max = Math.max(max, cur);
                cur = 1;
            } else {
                cur++;
            }
        }
        return max <= (temp.length + 1) / 2 ? "" : "";
    }

    // 771
    public int numJewelsInStones(String J, String S) {
        List<Character> chars = J.chars().mapToObj(e->(char)e).collect(Collectors.toList());
        HashSet<Character> jewels = new HashSet<Character>(chars);
        char[] stones = S.toCharArray();
        int res = 0;
        for (int i = 0; i < stones.length; i++) {
            if (jewels.contains(stones[i])) {
                res++;
            }
        }
        return res;
    }
}

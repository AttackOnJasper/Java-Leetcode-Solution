package com.jasperwang.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * String-focused LeetCode solutions and small text utilities.
 *
 * <p>The methods are grouped by problem domain rather than by difficulty. Most implementations are
 * self-contained so they can be copied into individual LeetCode submissions.
 */
public class StringQuestion {
    private int lo, maxLen;

    /**
     * LeetCode 5: finds the longest palindromic substring by expanding around each possible center.
     *
     * @param s input string
     * @return longest contiguous palindrome in {@code s}
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) return s;
        for (int i = 0; i < len - 1; i++) {
            longestPalindromeHelper(s, i, i); // assume odd length, try to extend Palindrome as possible
            longestPalindromeHelper(s, i, i + 1); // assume even length.
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

    /**
     * Encodes alphabetic characters with a Caesar shift while leaving non-letters unchanged.
     *
     * @param text source text
     * @param shift number of positions to shift letters forward
     * @return encoded text
     */
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

    /**
     * Decodes text encoded with the matching Caesar shift.
     *
     * @param text encoded text
     * @param shift number of positions originally shifted forward
     * @return decoded text
     */
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

    /**
     * LeetCode 14: returns the longest prefix shared by every string in the array.
     *
     * @param strs strings to compare
     * @return common prefix, or an empty string when none exists
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String res = strs[0];
        for (int i = 1; i < strs.length; i++) {
            char[] temp = strs[i].toCharArray();
            int j = 0;
            for (; j < temp.length; j++) {
                if (res.length() <= j || res.charAt(j) != temp[j]) break;
            }
            res = res.substring(0, j);
        }
        return res;
    }

    /**
     * LeetCode 20: validates whether brackets are balanced and properly nested.
     *
     * @param s string containing bracket characters
     * @return {@code true} when every opener is closed in stack order
     */
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

    /**
     * LeetCode 28: finds the first occurrence of {@code needle} in {@code haystack}.
     *
     * @param haystack string to search within
     * @param needle substring to find
     * @return starting index of the first match, or {@code -1}
     */
    public int strStr(String haystack, String needle) {
        for (int i = 0; ; i++) {
            for (int j = 0; ; j++) {
                if (j == needle.length()) return i;
                if (i + j == haystack.length()) return -1;
                if (needle.charAt(j) != haystack.charAt(i + j)) break;
            }
        }
    }

    /**
     * LeetCode 43: multiplies two non-negative integer strings without converting them to numeric
     * types.
     *
     * <p>Tip: use vertical multiplication. The product of the {@code i}th digit and {@code j}th
     * digit contributes to positions {@code i + j} and {@code i + j + 1}.
     *
     * @param num1 first non-negative integer
     * @param num2 second non-negative integer
     * @return product as a decimal string
     */
    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] product = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int partialProduct = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                // Keep the partial sum because the intermediate value can be larger than 10.
                int partialSum = partialProduct + product[i + j + 1];
                product[i + j] += partialSum / 10;
                product[i + j + 1] = partialSum % 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int num : product) if (num != 0 || sb.length() != 0) sb.append(num);
        return sb.length() == 0 ? "0" : sb.toString();
    }

    /**
     * LeetCode 49: groups anagrams together.
     *
     * <p>Tip: sort each string to create a stable key for detecting existing anagram groups.
     *
     * <p>This method is currently a placeholder and returns an empty result.
     *
     * @param strs strings to group
     * @return grouped anagrams
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        return res;
    }

    /**
     * LeetCode 67: adds two binary strings.
     *
     * @param a first binary number
     * @param b second binary number
     * @return binary representation of the sum
     */
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

    /**
     * LeetCode 125: tests whether a string is a palindrome after removing non-alphanumeric characters and
     * normalizing case.
     *
     * @param s input string
     * @return {@code true} when the normalized string reads the same backward
     */
    public static boolean isPalindrome(String s) {
        String temp = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String reversed = new StringBuffer(temp).reverse().toString();
        return reversed.equals(temp);
    }

    /**
     * LeetCode 161: checks whether two strings are exactly one insert, delete, or replace apart.
     *
     * @param s first string
     * @param t second string
     * @return {@code true} if the edit distance is exactly one
     */
    public boolean isOneEditDistance(String s, String t) {
        for (int i = 0; i < Math.min(s.length(), t.length()); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (s.length() == t.length()) return s.substring(i + 1).equals(t.substring(i + 1));
                if (s.length() < t.length()) return s.substring(i).equals(t.substring(i + 1));
                return t.substring(i).equals(s.substring(i + 1));
            }
        }
        return Math.abs(s.length() - t.length()) == 1;
    }

    /**
     * LeetCode 205: determines whether two strings have a one-to-one character pattern.
     *
     * @param s source pattern string
     * @param t target pattern string
     * @return {@code true} when matching positions preserve the same first-occurrence mapping
     */
    public boolean isIsomorphic(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        if (sLen != tLen) return false;
        int[] cs = new int[128], ct = new int[128];
        for (int i = 0; i < sLen; i++) {
            // Matching characters must share the same first-seen index.
            if (cs[s.charAt(i)] != ct[t.charAt(i)]) return false;
            if (cs[s.charAt(i)] == 0) {
                cs[s.charAt(i)] = i + 1; // prevents 0
                ct[t.charAt(i)] = i + 1;
            }
        }
        return true;
    }

    /**
     * LeetCode 246: checks whether a number string remains valid and equal after a 180-degree
     * rotation.
     *
     * @param num number string
     * @return {@code true} for strobogrammatic strings
     */
    public boolean isStrobogrammatic(String num) {
        HashMap<Character, Character> h = new HashMap<>();
        h.put('8', '8');
        h.put('1', '1');
        h.put('6', '9');
        h.put('9', '6');
        h.put('0', '0');

        int l = 0, r = num.length() - 1;
        while (l <= r) {
            if (!h.containsKey(num.charAt(l))) return false;
            if (h.get(num.charAt(l)) != num.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }

    /**
     * LeetCode 266: determines whether any permutation of the string can form a palindrome.
     *
     * @param s input string
     * @return {@code true} when at most one character has an odd count
     */
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

    /**
     * LeetCode 290: validates whether words follow a character pattern with a one-to-one mapping.
     *
     * @param pattern pattern characters
     * @param str space-separated words
     * @return {@code true} when the pattern maps consistently to words
     */
    public boolean wordPattern(String pattern, String str) {
        HashMap<Character, String> map = new HashMap<Character, String>();
        String[] splited = str.split(" ");
        char[] arr = pattern.toCharArray();
        if (arr.length != splited.length) return false;
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

    /**
     * LeetCode 293: generates all possible next moves for Flip Game by replacing one {@code "++"} with {@code
     * "--"}.
     *
     * <p>Example: one move replaces a single {@code "++"} run with {@code "--"}.
     *
     * @param s current game state
     * @return possible states after one move
     */
    public List<String> generatePossibleNextMoves(String s) {
        List<String> res = new LinkedList<>();
        for (int i = -1; (i = s.indexOf("++", i + 1)) >= 0; )
            res.add(s.substring(0, i) + "--" + s.substring(i + 2));
        return res;
    }

    /**
     * LeetCode 294: decides whether the first player can guarantee a Flip Game II win using
     * recursion with memoization.
     *
     * <p>Tip: solve recursively on each opponent state, or use dynamic programming over repeated
     * states.
     *
     * @param s current game state
     * @return {@code true} when the first player can force a win
     */
    public boolean canWin(String s) {
        if (s == null || s.length() < 2) return false;

        Set<String> winSet = new HashSet<String>();
        return canWin(s, winSet);
    }

    private boolean canWin(String s, Set<String> winSet) {
        if (winSet.contains(s)) return true;

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '+' && s.charAt(i + 1) == '+') {
                String sOpponent = s.substring(0, i) + "--" + s.substring(i + 2);
                if (!canWin(sOpponent, winSet)) {
                    winSet.add(s);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * LeetCode 294 alternative: determines the Flip Game II result using Grundy numbers.
     *
     * @param s current game state
     * @return {@code true} when the first player can force a win
     */
    public boolean canWin2(String s) {
        s = s.replace('-', ' ');
        int G = 0;
        List<Integer> g = new ArrayList<>();
        for (String t : s.split("[ ]+")) {
            int p = t.length();
            if (p == 0) continue;
            while (g.size() <= p) {
                char[] x = t.toCharArray();
                int i = 0, j = g.size() - 2;
                while (i <= j) x[g.get(i++) ^ g.get(j--)] = '-';
                g.add(new String(x).indexOf('+'));
            }
            G ^= g.get(p);
        }
        return G != 0;
    }

    /**
     * LeetCode 318: finds the maximum product of lengths for two words that share no letters.
     *
     * <p>Computes the maximum value of {@code length(words[i]) * length(words[j])} where the two
     * words do not share common letters.
     *
     * @param words candidate words
     * @return maximum length product
     */
    public static int maxProduct(String[] words) {
        if (words == null || words.length == 0) return 0;
        int len = words.length;
        int[] value = new int[len];
        for (int i = 0; i < len; i++) {
            String tmp = words[i];
            value[i] = 0;
            for (int j = 0; j < tmp.length(); j++)
                // Store each word as a bit mask of its letters.
                value[i] |= 1 << (tmp.charAt(j) - 'a');
        }
        int maxProduct = 0;
        for (int i = 0; i < len; i++)
            for (int j = i + 1; j < len; j++)
                // Two words have no shared letters when their masks do not overlap.
                if ((value[i] & value[j]) == 0 && (words[i].length() * words[j].length() > maxProduct))
                    maxProduct = words[i].length() * words[j].length();
        return maxProduct;
    }

    /**
     * LeetCode 340: gets the length of the longest substring with at most {@code k} distinct
     * characters using a sliding window.
     *
     * @param s input string
     * @param k maximum number of distinct characters allowed
     * @return longest valid window length
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int res = 0, low = 0, numOfDistinctChars = 0;
        int[] arr = new int[256];
        for (int high = 0; high < s.length(); high++) {
            if (arr[s.charAt(high)]++ == 0) numOfDistinctChars++;
            if (numOfDistinctChars > k) {
                // Remove characters until one distinct character leaves the window.
                while (--arr[s.charAt(low++)] > 0)
                    ;
                numOfDistinctChars--;
            }
            res = Math.max(res, high - low + 1);
        }
        return res;
    }

    /**
     * LeetCode 394: decodes bracketed repeat expressions such as {@code "3[a2[c]]"}.
     *
     * <p>Example: {@code "3[a2[c]]"} decodes to {@code "accaccacc"}.
     *
     * @param s encoded expression
     * @return decoded string
     */
    public String decodeString(String s) {
        Deque<Integer> intStack = new ArrayDeque<>();
        Deque<StringBuilder> strStack = new ArrayDeque<>();
        StringBuilder cur = new StringBuilder();
        int num = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '[') {
                intStack.push(num);
                strStack.push(cur);
                cur = new StringBuilder();
                num = 0;
            } else if (c == ']') {
                int times = intStack.pop();
                StringBuilder temp = cur; // string inside [ ]
                cur = strStack.pop(); // string before [ ]
                while (times-- > 0) {
                    cur.append(temp);
                }
            } else {
                cur.append(c);
            }
        }
        return cur.toString();
    }

    /**
     * LeetCode 415: adds two non-negative integer strings.
     *
     * @param num1 first non-negative integer
     * @param num2 second non-negative integer
     * @return decimal sum as a string
     */
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        for (int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
             i >= 0 || j >= 0 || carry == 1;
             i++, j++) {
            int d1 = i < 0 ? 0 : num1.charAt(i) - '0';
            int d2 = j < 0 ? 0 : num2.charAt(j) - '0';
            sb.append((d1 + d2 + carry) % 10);
            carry = (d1 + d2 + carry) / 10;
        }
        return sb.reverse().toString();
    }

    /**
     * LeetCode 434: counts the number of segments in a string after splitting on spaces.
     *
     * <p>Example: splitting {@code " a b c"} by spaces yields three non-empty segments.
     *
     * @param s input string
     * @return number of whitespace-separated segments
     */
    public int countSegments(String s) {
        return ("x " + s).split(" +").length - 1;
    }

    /**
     * LeetCode 438: finds every start index where {@code p}'s anagram appears in {@code s}.
     *
     * @param s source string
     * @param p pattern string
     * @return start indices of anagram windows
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0) return list;
        int[] hash = new int[256]; // character hash
        // record each character in p to hash
        for (char c : p.toCharArray()) {
            hash[c]++;
        }
        // two points, initialize count to p's length
        int left = 0, right = 0, count = p.length();
        while (right < s.length()) {
            // move right everytime, if the character exists in p's hash, decrease the count
            // current hash value >= 1 means the character is existing in p
            if (hash[s.charAt(right++)]-- >= 1) count--;

            // found the right anagram
            if (count == 0) list.add(left);

            // if we find the window's size equals to p, then we have to move left (narrow the window) to
            // find the new match window
            // ++ to reset the hash because we kicked out the left
            // only increase the count if the character is in p
            // the count >= 0 indicate it was original in the hash, cuz it won't go below 0
            if (right - left == p.length() && hash[s.charAt(left++)]++ >= 0) count++;
        }
        return list;
    }

    /**
     * LeetCode 439: evaluates an arbitrarily nested ternary expression from right to left.
     *
     * <p>Tip: scan from right to left with a stack. Example: {@code "T?2:3"} evaluates to {@code
     * "2"}.
     *
     * @param expression ternary expression
     * @return evaluated expression result
     */
    public static String parseTernary(String expression) {
        if (expression == null || expression.length() == 0) return "";
        Deque<Character> stack = new LinkedList<>();

        for (int i = expression.length() - 1; i >= 0; i--) {
            char c = expression.charAt(i);
            if (!stack.isEmpty() && stack.peek() == '?') {

                stack.pop(); // pop '?'
                char first = stack.pop();
                stack.pop(); // pop ':'
                char second = stack.pop();

                if (c == 'T') stack.push(first);
                else stack.push(second);
            } else {
                stack.push(c);
            }
        }

        return String.valueOf(stack.peek());
    }

    /**
     * LeetCode 459: checks whether a string is composed of repeated copies of a smaller substring.
     *
     * @param s input string
     * @return {@code true} when a repeating substring pattern exists
     */
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

    int count = 0;

    /**
     * LeetCode 647: counts palindromic substrings by expanding around each center.
     *
     * @param s input string
     * @return number of palindromic substrings
     */
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        for (int i = 0; i < s.length(); i++) { // i is the mid point
            extendPalindrome(s, i, i); // odd length;
            extendPalindrome(s, i, i + 1); // even length
        }
        return count;
    }

    private void extendPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
    }

    /**
     * LeetCode 680: checks whether a string can become a palindrome after deleting at most one
     * character.
     *
     * @param s input string
     * @return {@code true} when the string is already valid or can be fixed with one deletion
     */
    public boolean validPalindrome(String s) {
        int i = -1, j = s.length();
        while (++i < --j) {
            if (s.charAt(i) != s.charAt(j)) {
                // either remove i or remove j
                return isPalindromic(s, i, j + 1) || isPalindromic(s, i - 1, j);
            }
        }
        return true;
    }

    private boolean isPalindromic(String s, int l, int r) {
        while (++l < --r) if (s.charAt(l) != s.charAt(r)) return false;
        return true;
    }

    /**
     * LeetCode 681: finds the next closest time that can be formed by reusing the original digits.
     *
     * <p>Digits from the original {@code HH:mm} input may be reused any number of times.
     *
     * @param time time in {@code HH:mm} format
     * @return next valid time, wrapping past midnight if needed
     */
    public String nextClosestTime(String time) {
        int[] arr =
                new int[]{
                        time.charAt(0) - '0', time.charAt(1) - '0', time.charAt(3) - '0', time.charAt(4) - '0'
                };
        Set<Integer> set = IntStream.of(arr).boxed().collect(Collectors.toSet());
        int curTime =
                60 * Integer.parseInt(time.substring(0, 2)) + Integer.parseInt(time.substring(3, 5));
        while (true) {
            curTime = (curTime + 1) % 1440;
            int[] digits =
                    new int[]{curTime / 60 / 10, curTime / 60 % 10, curTime % 60 / 10, curTime % 60 % 10};
            for (int i = 0; ; i++) {
                if (!set.contains(digits[i])) break;
                if (i == digits.length - 1) return String.format("%02d:%02d", curTime / 60, curTime % 60);
            }
        }
    }

    /**
     * LeetCode 681 variation: finds the next closest time using each original digit at most as often
     * as it appears.
     *
     * <p>Unlike the standard problem, each original digit can only be used once per generated time.
     *
     * @param S time in {@code HH:mm} format
     * @return next valid time, wrapping past midnight if needed
     */
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
            int[] digits =
                    new int[]{curTime / 60 / 10, curTime / 60 % 10, curTime % 60 / 10, curTime % 60 % 10};
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

    /**
     * LeetCode 696: counts binary substrings with equal grouped runs of zeroes and ones.
     *
     * <p>Valid substrings must have the same number of zeroes and ones, with all zeroes grouped
     * together and all ones grouped together.
     *
     * @param s binary string
     * @return number of valid grouped binary substrings
     */
    public int countBinarySubstrings(String s) {
        char[] arr = s.toCharArray();
        int res = 0, prev = 0, curr = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                curr++;
            } else {
                prev = curr;
                curr = 1;
            }
            if (prev >= curr) res++;
        }
        return res;
    }

    /**
     * LeetCode 767: reorganizes characters so no adjacent characters are equal.
     *
     * @param S input string
     * @return reorganized string, or an empty string when impossible
     */
    public String reorganizeString(String S) {
        // Create map of each char to its count
        Map<Character, Integer> map = new HashMap<>();
        for (char c : S.toCharArray()) {
            int count = map.getOrDefault(c, 0) + 1;
            // Impossible to form a solution
            if (count > (S.length() + 1) / 2) return "";
            map.put(c, count);
        }
        // Greedy: fetch char of max count as next char in the result.
        // Use PriorityQueue to store pairs of (char, count) and sort by count DESC.
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (char c : map.keySet()) {
            pq.add(new int[]{c, map.get(c)});
        }
        // Build the result.
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int[] first = pq.poll();
            if (sb.length() == 0 || first[0] != sb.charAt(sb.length() - 1)) {
                sb.append((char) first[0]);
                if (--first[1] > 0) {
                    pq.add(first);
                }
            } else {
                int[] second = pq.poll();
                sb.append((char) second[0]);
                if (--second[1] > 0) {
                    pq.add(second);
                }
                pq.add(first);
            }
        }
        return sb.toString();
    }

    /**
     * LeetCode 771: counts how many stones are jewels.
     *
     * @param J characters representing jewel types
     * @param S characters representing stones
     * @return number of stones that are jewels
     */
    public int numJewelsInStones(String J, String S) {
        List<Character> chars = J.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
        HashSet<Character> jewels = new HashSet<Character>(chars);
        char[] stones = S.toCharArray();
        int res = 0;
        for (int i = 0; i < stones.length; i++) if (jewels.contains(stones[i])) res++;
        return res;
    }

    /**
     * LeetCode 422: validates whether the word list reads the same horizontally and vertically.
     *
     * @param words candidate word square rows
     * @return {@code true} when each row/column character matches
     */
    public boolean validWordSquare(List<String> words) {
        for (int i = 0, size = words.size(); i < size; i++) {
            String temp = words.get(i);
            for (int j = 0, len = temp.length(); j < len; j++) {
                if (size <= j || words.get(j).length() <= i || words.get(j).charAt(i) != temp.charAt(j))
                    return false;
            }
        }
        return true;
    }

    /**
     * LeetCode 443: compresses consecutive character groups in-place.
     *
     * <p>Example: {@code ["a","a","b","b","c","c","c"]} compresses to the prefix {@code
     * ["a","2","b","2","c","3"]}.
     *
     * @param chars mutable character array
     * @return length of the compressed prefix
     */
    public int compress(char[] chars) {
        int indexAns = 0, index = 0;
        while (index < chars.length) {
            char currentChar = chars[index];
            int count = 0;
            while (index < chars.length && chars[index] == currentChar) {
                index++;
                count++;
            }
            chars[indexAns++] = currentChar;
            if (count != 1) for (char c : Integer.toString(count).toCharArray()) chars[indexAns++] = c;
        }
        return indexAns;
    }

    /**
     * LeetCode 592: adds and subtracts fractions represented in a single expression.
     *
     * @param expression expression such as {@code "-1/2+1/2+1/3"}
     * @return reduced fraction in {@code numerator/denominator} form
     */
    public String fractionAddition(String expression) {
        Scanner sc = new Scanner(expression).useDelimiter("/|(?=[-+])");
        int A = 0, B = 1;
        while (sc.hasNext()) {
            int a = sc.nextInt(), b = sc.nextInt();
            A = A * b + a * B;
            B *= b;
            int g = gcd(A, B);
            A /= g;
            B /= g;
        }
        return A + "/" + B;
    }

    private int gcd(int a, int b) {
        return a != 0 ? gcd(b % a, a) : Math.abs(b);
    }
}

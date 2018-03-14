package main.java;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringQuestion {
    // 5 Longest Palindrome Substring
    private int lo, maxLen;
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) return s;
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

    // 43
    /** 列竖式计算 ith digit * jth digit -> product lie on (i+j, j+i+1) */
    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] product = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int partialProduct = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                /** need to use partialSum as partial result might be larger than 10 */
                int partialSum = partialProduct + product[i + j + 1];
                product[i + j] += partialSum / 10;
                product[i + j + 1] = partialSum % 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int num : product)
            if (num != 0 || sb.length() != 0)
                sb.append(num);
        return sb.length() == 0 ? "0" : sb.toString();
    }

    // 49 Group anagrams: Given an array of strings, group anagrams together
    /** find a way to identify if anagram exists in the result (hint: sort) */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        return res;
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

    // 161
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

    // 293 Flip game: replace two "++" with "--"
    public List<String> generatePossibleNextMoves(String s) {
        List<String> res = new LinkedList<>();
        for (int i=-1; (i = s.indexOf("++", i+1)) >= 0; )
            res.add(s.substring(0, i) + "--" + s.substring(i+2));
        return res;
    }

    // 340. Get length of longest substring with at most K distinct characters
    /** sliding window */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int res = 0, low = 0, numOfDistinctChars = 0;
        int[] arr = new int[256];
        for (int high = 0; high < s.length(); high++) {
            if (arr[s.charAt(high)]++ == 0) numOfDistinctChars++;
            if (numOfDistinctChars > k) {
                /** while there is still this char in the window after removal, move on and remove the next one until a distinct char is removed */
                while (--arr[s.charAt(low++)] > 0);
                numOfDistinctChars--;
            }
            res = Math.max(res, high - low + 1);
        }
        return res;
    }

    // 345 Reverse Vowels
    /** two pointer */

    // 394. Decode string: 3[a2[c]] -> accaccacc
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
                StringBuilder temp = cur;  // string inside [ ]
                cur = strStack.pop();  // string before [ ]
                while (times-- > 0) {
                    cur.append(temp);
                }
            } else {
                cur.append(c);
            }
        }
        return cur.toString();
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

            // found the right anagram
            if (count == 0) list.add(left);

            //if we find the window's size equals to p, then we have to move left (narrow the window) to find the new match window
            //++ to reset the hash because we kicked out the left
            //only increase the count if the character is in p
            //the count >= 0 indicate it was original in the hash, cuz it won't go below 0
            if (right - left == p.length() && hash[s.charAt(left++)]++ >= 0) count++;
        }
        return list;
    }

    // 439. Given a string representing arbitrarily nested ternary expressions, calculate the result of the expression
    // e.g. "T?2:3" -> 2
    /** Evaluate from right to left (hint: stack) */
    public static String parseTernary(String expression) {
        if (expression == null || expression.length() == 0) return "";
        Deque<Character> stack = new LinkedList<>();

        for (int i = expression.length() - 1; i >= 0; i--) {
            char c = expression.charAt(i);
            if (!stack.isEmpty() && stack.peek() == '?') {

                stack.pop(); //pop '?'
                char first = stack.pop();
                stack.pop(); //pop ':'
                char second = stack.pop();

                if (c == 'T') stack.push(first);
                else stack.push(second);
            } else {
                stack.push(c);
            }
        }

        return String.valueOf(stack.peek());
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
            pq.add(new int[] {c, map.get(c)});
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

    // 771
    public int numJewelsInStones(String J, String S) {
        List<Character> chars = J.chars().mapToObj(e->(char)e).collect(Collectors.toList());
        HashSet<Character> jewels = new HashSet<Character>(chars);
        char[] stones = S.toCharArray();
        int res = 0;
        for (int i = 0; i < stones.length; i++)
            if (jewels.contains(stones[i]))
                res++;
        return res;
    }
}

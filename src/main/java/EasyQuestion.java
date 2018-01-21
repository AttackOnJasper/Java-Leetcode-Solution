package main.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.List;

public class EasyQuestion {
    // 9
    public boolean isPalindrome(int x) {
        if(x < 0) return false;
        int y = x;
        int res = 0;
        while(y != 0) {
            res = res * 10 + y % 10;
            y /= 10;
        }
        return x == res;
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

    // 69
    /** Newton's method */
    public int mySqrt(int x) {
        long r = x;
        while (r*r > x)
            r = (r + x/r) / 2;
        return (int) r;
    }

    // 83
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        ListNode list = head;
        while(list.next != null) {
            if (list.val == list.next.val) {
                list.next = list.next.next;
            } else {
                list = list.next;
            }
        }
        return head;
    }

    // 125
    public static boolean isPalindrome(String s) {
        String temp = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String reversed = new StringBuffer(temp).reverse().toString();
        return reversed.equals(temp);
    }

    // 155 Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
    /**
     * push the difference between min & the # to be pushed
     */
    class MinStack {
        long min;
        final Stack<Long> stack;

        public MinStack() {
            stack = new Stack<>();
        }

        public void push(int x) {
            if (stack.isEmpty()) {
                stack.push(0L);
                min = x;
            } else {
                stack.push(
                    x - min); // need to change to previous min during pop if x is smaller than min
                if (x < min) {
                    min = x;
                }
            }
        }

        public void pop() {
            if (stack.isEmpty()) {
                return;
            }
            final long pop = stack.pop();
            if (pop < 0) {
                min = min - pop; // If negative, increase the min value
            }
        }

        public int top() {
            final long top = stack.peek();
            if (top > 0) {
                return (int) (top + min);
            } else {
                return (int) (min);
            }
        }

        public int getMin() {
            return (int) min;
        }
    }

    class MinStack2 {
        int min = Integer.MAX_VALUE;
        Stack<Integer> stack = new Stack<Integer>();
        public void push(int x) {
            // only push the old minimum value when min would be updated to x
            if(x <= min){
                stack.push(min);
                min=x;
            }
            stack.push(x);
        }

        public void pop() {
            // if pop operation could result in the changing of the current minimum value,
            // pop twice and change the current minimum value to the last minimum value.
            if(stack.pop() == min) min = stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min;
        }
    }

    // 170
    public class TwoSum {
        Set<Integer> num;
        Set<Integer> sum;

        TwoSum() {
            num = new HashSet<Integer>();
            sum = new HashSet<Integer>();
        }

        public void add(int number) {
            if (num.contains(number)) {
                sum.add(number * 2);
            } else {
                num.add(number);
                Iterator<Integer> it = num.iterator();
                while (it.hasNext()) {
                    sum.add(it.next() + number);
                }
            }
        }

        public boolean find(int value) {
            return sum.contains(value);
        }
    }

    // 191 Hamming weight (bitCount)
    // bit manipulation
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }
    // bit shift
    public int hammingWeight2(int n) {
        int count = 0;
        while (n != 0) {
            count += n & 1;
            n = n >>> 2;
        }
        return count;
    }

    // 202
    public boolean isHappy(int n) {
        int i1 = n, i2 = next(n);
        while (i2 != i1) {
            i1 = next(i1);
            i2 = next(next(i2));
        }
        return i1 == 1;
    }
    private int next(int n) {
        int res = 0;
        while (n != 0) {
            int t = n % 10;
            res += t * t;
            n /= 10;
        }
        return res;
    }

    // 203 Remove Linked List Elements
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }
    public ListNode removeElements2(ListNode head, int val) {
        if (head == null) return head;
        ListNode curr = head;
        while (curr.next != null) {
            if (curr.next.val == val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return head.val == val? head.next : head;
    }

    // 204 Count Primes
    /** semi-dp idea */
    public int countPrimes(int n) {
        int res = 0;
        boolean[] notPrime = new boolean[n];
        for (int i = 2; i < n; i++) {
            if (!notPrime[i]) {
                res++;
                for (int j = 2; i * j < n; j++) {
                    notPrime[i * j] = true;
                }
            }
        }
        return res;
    }

    // 206 Reverse Linked List
    public ListNode reverseList1(ListNode head) {
        return helper(head, null);
    }
    private ListNode helper(ListNode head, ListNode newHead) {
        if (head == null) return newHead;
        ListNode next = head.next;
        head.next = newHead;
        return helper(next, head);
    }

    public ListNode reverseList2(ListNode head) {
    /* iterative solution */
        ListNode newHead = null;
        while (head != null) {
            ListNode next = head.next;
            // build new list from end to start
            head.next = newHead;
            newHead = head;
            // move to next node
            head = next;
        }
        return newHead;
    }

    // 234  Palindrome Linked List (in O(n) time and O(1) space)
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        if (fast != null) { // odd nodes: let right half smaller
            slow = slow.next;
        }
        slow = reverseList1(slow);
        fast = head;

        while (slow != null) {
            if (fast.val != slow.val) {
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }

    // 252 Meeting rooms
    private class Interval {
        int start;
        int end;
    }
    public boolean canAttendMeetings(Interval[] intervals) {
        int len=intervals.length;
        if(len==0) return true;
        int[]begin=new int[len];
        int[]stop=new int[len];
        for(int i=0;i<len;i++){
            begin[i]=intervals[i].start;
            stop[i]=intervals[i].end;
        }
        Arrays.sort(begin);
        Arrays.sort(stop);
        for(int i=1;i<len;i++){
            if(begin[i]<stop[i-1]) return false;
        }
        return true;
    }
    public boolean canAttendMeetings2(Interval[] intervals) {
        if (intervals == null)
            return false;
        // Sort the intervals by start time
        Arrays.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval a, Interval b) { return a.start - b.start; }
        });
        for (int i = 1; i < intervals.length; i++)
            if (intervals[i].start < intervals[i - 1].end)
                return false;
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

    // 278
    private boolean isBadVersion(int n) {
        return n % 2 == 0;
    }
    // binary search
    public int firstBadVersion(int n) {
        int low = 1, high = n;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (isBadVersion(mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
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

    // 326 Power of Three
    public boolean isPowerOfThree(int n) {
        // 1162261467 is 3^19,  3^20 is bigger than int
        return ( n>0 &&  1162261467%n==0);
    }
    // 231
    public boolean isPowerOfTwo(int n) {
        return n>0 && ((n & (n-1)) == 0);
    }
    // 342
    public boolean isPowerOfFour(int num) {
        return num > 0 && (num & (num - 1)) == 0 && (num - 1) % 3 == 0;
    }

    // 367 perfect square
    // sequence 1 + 3 + 5 + 7
    public boolean isPerfectSquare(int num) {
        if (num < 1) return false;
        for (int i = 1; num > 0; i += 2)
            num -= i;
        return num == 0;
    }
    // binary search to find the square root
    public boolean isPerfectSquare1(int num) {
        if (num < 1) return false;
        long left = 1, right = num;// long type to avoid 2147483647 case

        while (left <= right) {
            long mid = left + (right - left) / 2;
            long t = mid * mid;
            if (t > num) {
                right = mid - 1;
            } else if (t < num) {
                left = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }
    // newton's method to find the square root
    boolean isPerfectSquare2(int num) {
        if (num < 1) return false;
        if (num == 1) return true;
        long t = num / 2;
        while (t * t > num) {
            t = (t + num / t) / 2;
        }
        return t * t == num;
    }

    // 371 Get Sum
    // Implement sum without +
    public int getSum(int a, int b) {
        return b == 0 ? a : getSum(a ^ b, (a & b) << 1);
    }

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

    // 405 Num to Hex
    private char[] map = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

    public String toHex(int num) {
        if(num == 0) return "0";
        String result = "";
        while(num != 0){
            result = map[(num & 15)] + result;
            num = (num >>> 4); // >> is arithmetic shift right, >>> is logical shift right
        }
        return result;
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

    // 422 Valid Word Square: verify that the transpose of matrix is the same as matrix
    public boolean validWordSquare(List<String> words) {
        for (int i = 0, size = words.size(); i < size; i++) {
            String temp = words.get(i);
            for (int j = 0, len = temp.length(); j < len; j++) {
                if (size <= j || words.get(j).length() <= i || words.get(j).charAt(i) != temp.charAt(j)) return false;
            }
        }
        return true;
    }

    // 434
    /** " a b c" split -> "" "a" "b" "c" */
    public int countSegments(String s) {
        return ("x " + s).split(" +").length - 1;
    }

    // 443
    public static int compress(char[] chars) {
        int newLength = 1, count = 1;
        for (int i = 1; i < chars.length + 1; i++) {
            if (i != chars.length && chars[i] != chars[i-1]) {
                count++;
            } else {
                if (count > 1) {
                    int low = newLength;
                    for (; count > 0; count /= 10) {
                        chars[newLength++] = (char)(count%10 + '0');
                    }
                    int high = newLength;
                    reverse(chars, low, high);
                }
                chars[newLength++] = chars[i];
                count = 1;
            }
        }
        return newLength;
    }
    private static void reverse(char[] chars, int low, int high) {
        for (int i = low, j = high - 1; i < j; i++, j--) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
    }

    // 447

    /**
     * find the number of 2 points that have same distance towards one point
     */
    public int numberOfBoomerangs(int[][] points) {
        int res = 0;
        // store the distance & number of coordinates for that distance
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<points.length; i++) {
            for(int j=0; j<points.length; j++) {
                if(i == j) continue;
                int d = getDistance(points[i], points[j]);
                map.put(d, map.getOrDefault(d, 0) + 1);
            }
            for(int val : map.values()) {
                res += val * (val-1);
            }
            map.clear();
        }
        return res;
    }
    private int getDistance(int[] a, int[] b) {
        int dx = a[0] - b[0];
        int dy = a[1] - b[1];

        return dx*dx + dy*dy;
    }

    // 461 Hamming distance
    public int hammingDistance(int x, int y) {
        /*
        int bitCount(int n) {
            while(n) {
                n = n & (n-1);
                count++;
            }
            return count;
        }
         */
        return Integer.bitCount(x ^ y);
    }

    // 463. Island Perimeter
    public int islandPerimeter(int[][] grid) {
        int numOfIslands = 0, neighbours = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    numOfIslands++;
                    if (i < grid.length - 1 && grid[i + 1][j] == 1) {
                        neighbours++; // count down neighbours
                    }
                    if (j < grid[i].length - 1 && grid[i][j + 1] == 1) {
                        neighbours++; // count right neighbours
                    }
                }
            }
        }
        return numOfIslands * 4 - neighbours * 2;
    }

    // 476 Number Complement
    public int findComplement(int num) {
        return (largest_power(num) << 1) - 1 - num;
    }
    // Equal to highestOneBit
    private int largest_power(int N) {
        //changing all right side bits to 1.
        N = N | (N >> 1);
        N = N | (N >> 2);
        N = N | (N >> 4);
        N = N | (N >> 8);
        N = N | (N >> 16);
        return (N + 1) >> 1;
    }

    public int findComplement2(int num) {
        // highestOneBit(n) returns 2^m, where 2^m < n < 2^(m+1)
        return ~num & ((Integer.highestOneBit(num) << 1) - 1);
    }
}

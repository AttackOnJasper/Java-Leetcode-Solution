package main;

import java.util.ArrayList;
import java.util.Arrays;
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
        if (x < 0 || (x != 0 && x % 10 == 0)) {
            return false;
        }
        int rev = 0;
        while (x > rev) {
            rev = rev * 10 + x % 10;
            x = x / 10;
        }
        return (x == rev || x == rev / 10);
    }

    // 13
    public static int romanToInt(String s) {
        int res = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            switch (c) {
                case 'I':
                    res += (res >= 5 ? -1 : 1);
                    break;
                case 'V':
                    res += 5;
                    break;
                case 'X':
                    res += 10 * (res >= 50 ? -1 : 1);
                    break;
                case 'L':
                    res += 50;
                    break;
                case 'C':
                    res += 100 * (res >= 500 ? -1 : 1);
                    break;
                case 'D':
                    res += 500;
                    break;
                case 'M':
                    res += 1000;
                    break;
            }
        }
        return res;
    }

    // 20
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

    // 35 binary search
    public int searchInsert(int[] A, int target) {
        int low = 0, high = A.length-1;
        while(low<=high){
            int mid = (low+high)/2;
            if(A[mid] == target) return mid;
            else if(A[mid] > target) high = mid-1;
            else low = mid+1;
        }
        return low;
    }

    // 53 Max subarray

    // DP dp[i]: max subarray that ends with nums[i]
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = nums[i] + (dp[i-1] > 0 ? dp[i-1] : 0);
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    // Divide-and-Conquer
    public int maxSubArray1(int[] nums) {
        if (nums.length == 0) return 0;
        return maxSubArray1Helper(nums, 0, nums.length - 1);
    }

    private int maxSubArray1Helper(final int[] nums, final int low, final int high) {
        if (low >= high) {
            return nums[low];
        }
        final int mid = (low + high) / 2;
        final int leftans = maxSubArray1Helper(nums, low, mid);
        final int rightans = maxSubArray1Helper(nums, mid + 1, high);
        int leftmax = nums[mid];
        int rightmax = nums[mid + 1];
        int temp = 0;
        for (int i = mid; i >= low; i--) {
            temp += nums[i];
            if (temp > leftmax) {
                leftmax = temp;
            }
        }
        temp = 0;
        for (int i = mid + 1; i <= high; i++) {
            temp += nums[i];
            if (temp > rightmax) {
                rightmax = temp;
            }
        }
        return Math.max(Math.max(rightans, leftans), rightmax + leftmax);
    }

    // Greedy
    public int maxSubArray2(int[] nums) {
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
        ListNode list = head;

        while(list != null) {
            if (list.next == null) {
                break;
            }
            if (list.val == list.next.val) {
                list.next = list.next.next;
            } else {
                list = list.next;
            }
        }

        return head;
    }


    // 88
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        int i = 0, j = 0, newIndex = 0;
        int[] temp = nums1.clone();
        while (i < m && j < n) {
            if (temp[i] <= nums2[j]) {
                nums1[newIndex++] = temp[i++];
            } else {
                nums1[newIndex++] = nums2[j++];
            }
        }
        while (i < m) {
            nums1[newIndex++] = temp[i++];
        }
        while (j < n) {
            nums1[newIndex++] = nums2[j++];
        }
    }

    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n -1, newIndex = m + n -1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[newIndex--] = nums1[i--];
            } else {
                nums1[newIndex--] = nums2[j--];
            }
        }
        while (i >= 0) {
            nums1[newIndex--] = nums1[i--];
        }
        while (j >= 0) {
            nums1[newIndex--] = nums2[j--];
        }
    }

    public void merge3(int[] nums1, int m, int[] nums2, int n) {
        while (n > 0) {
            nums1[m + n - 1] = (m == 0 || nums2[n - 1] > nums1[m - 1]) ? nums2[--n] : nums1[--m];
        }
    }

    // 101
    public boolean isSymmetric(TreeNode root) {
        return root == null || isSymmetricHelper(root.left, root.right);
    }

    private boolean isSymmetricHelper(TreeNode left, TreeNode right) {
        if (left == null || right == null) return left == right;
        if (left.val != right.val) return false;
        return isSymmetricHelper(left.left, right.right) && isSymmetricHelper(right.left, left.right);
    }

    // 104
    public int maxDepth(TreeNode root) {
        return root == null? 0 : 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    // 108 Sorted Array to BST
    public TreeNode sortedArrayToBST(int[] num) {
        if (num.length == 0) return null;
        return sortedArrayToBSTHelper(num, 0, num.length - 1);
    }

    private TreeNode sortedArrayToBSTHelper(int[] num, int low, int high) {
        if (low > high) {
            return null;
        }
        int mid = (low + high) / 2;
        TreeNode res = new TreeNode(num[mid]);
        res.left = sortedArrayToBSTHelper(num, low, mid - 1);
        res.right = sortedArrayToBSTHelper(num, mid + 1, high);
        return res;
    }

    // 110
    public boolean isBalanced(TreeNode root) {
        return isBalancedHelper(root) != -1;
    }

    private int isBalancedHelper(TreeNode root) {
        if (root == null) return 0;
        int left = isBalancedHelper(root.left);
        if (left == -1) return -1;
        int right = isBalancedHelper(root.right);
        if (right == -1) return -1;
        if (Math.abs(left - right) > 1) return -1;
        return 1 + Math.max(left, right);
    }

    // 111
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null) {
            return minDepth(root.right) + 1;
        }
        if (root.right == null) {
            return minDepth(root.left) + 1;
        }
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    // 125
    public boolean isPalindrome(String s) {
        String temp = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String reversed = new StringBuffer(temp).reverse().toString();
        return reversed.equals(temp);
    }

    public boolean isPalindrome2(String s) {
        if (s.isEmpty()) {
            return true;
        }
        int head = 0, tail = s.length() - 1;
        char cHead, cTail;
        while(head <= tail) {
            cHead = s.charAt(head);
            cTail = s.charAt(tail);
            if (!Character.isLetterOrDigit(cHead)) {
                head++;
            } else if(!Character.isLetterOrDigit(cTail)) {
                tail--;
            } else {
                if (Character.toLowerCase(cHead) != Character.toLowerCase(cTail)) {
                    return false;
                }
                head++;
                tail--;
            }
        }

        return true;
    }


    // 136
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i : nums) {
            res ^= i;
        }
        return res;
    }

    // 155
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
            // only push the old minimum value when the current
            // minimum value changes after pushing the new value x
            if(x <= min){
                stack.push(min);
                min=x;
            }
            stack.push(x);
        }

        public void pop() {
            // if pop operation could result in the changing of the current minimum value,
            // pop twice and change the current minimum value to the last minimum value.
            if(stack.pop() == min) min=stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min;
        }
    }

    // 167 TwoSumSorted
    public int[] twoSum(int[] numbers, int target) {
        int i = 0, j = numbers.length - 1;
        while (i < j) {
            if (numbers[i] + numbers[j] == target) break;
            if (numbers[i] + numbers[j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return new int[]{i + 1, j + 1};
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


    // 203 Remove Linked List Elements
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }

    public ListNode removeElements2(ListNode head, int val) {
        ListNode fakeHead = new ListNode(-1);
        fakeHead.next = head;
        ListNode curr = head, prev = fakeHead;
        while (curr != null) {
            if (curr.val == val) {
                prev.next = curr.next;
            } else {
                prev = prev.next;
            }
            curr = curr.next;
        }
        return fakeHead.next;
    }

    // 204 Count Primes
    public int countPrimes(int n) {
        int res = 0;
        boolean[] notPrime = new boolean[n];
        for (int i = 2; i < n; i++) {
            if (notPrime[i] == false) {
                res++;
                for (int j = 2; i * j < n; j++) {
                    notPrime[i * j] = true;
                }
            }
        }
        return res;
    }

    // 205 Isomorphic
    public boolean isIsomorphic(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        if (sLen != tLen) return false;
        int[] cs = new int[128], ct = new int[128];
        for (int i = 0; i < sLen; i++) {
            if (cs[s.charAt(i)] != ct[t.charAt(i)]) return false;
            if (cs[s.charAt(i)] == 0) {
                cs[s.charAt(i)] = i + 1;
                ct[t.charAt(i)] = i + 1;
            }
        }
        return true;
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
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }

    // 216
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode newLeft = invertTree(root.right);
        TreeNode newRight = invertTree(root.left);
        root.left = newLeft;
        root.right = newRight;
        return root;
    }

    // 219
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>();
        for(int i = 0; i < nums.length; i++){
            if(i > k) set.remove(nums[i-k-1]);
            if(!set.add(nums[i])) return true;
        }
        return false;
    }

    // 344
    public String reverseString(String s){
        return new StringBuilder(s).reverse().toString();
    }

    public String reverseString2(String s) {
        char[] arr = s.toCharArray();
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
        return String.valueOf(arr);
    }

    public String reverseString3(String s) {
        char[] arr = s.toCharArray();
        for (int start = 0, end = arr.length - 1; start < end; start++, end--) {
            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
        }
        return String.valueOf(arr);
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
        slow = reverse(slow);
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

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    // 235. Lowest Common Ancestor
    /**
     * Just walk down from the whole tree's root as long as both p and q are in the same subtree
     * (meaning their values are both smaller or both larger than root's)
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while ((root.val - p.val) * (root.val - q.val) > 0)
            root = p.val < root.val ? root.left : root.right;
        return root;
    }


    // 252 Meeting rooms
    private class Interval {
        int start;
        int end;
    }

    public boolean canAttendMeetings(Interval[] intervals) {
        int len=intervals.length;
        if(len==0){
            return true;
        }
        int[]begin=new int[len];
        int[]stop=new int[len];
        for(int i=0;i<len;i++){
            begin[i]=intervals[i].start;
            stop[i]=intervals[i].end;
        }
        Arrays.sort(begin);
        Arrays.sort(stop);
        for(int i=1;i<len;i++){
            if(begin[i]<stop[i-1]){
                return false;
            }
        }
        return true;
    }

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

    // 270 Closest Binary Search Tree Value
    public int closestValue(TreeNode root, double target) {
        int a = root.val;
        TreeNode child = (a < target) ? root.right : root.left;
        if (child == null) return a;
        int b = closestValue(child, target);
        return Math.abs(a - target) < Math.abs(b - target) ? a : b;
    }

    // 278
    private boolean isBadVersion(int n) {
        return true;
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

    // 283. Move Zero
    public void moveZeroes(int[] nums) {
        int i = 0;
        for (int n : nums) {
            if (n != 0) {
                nums[i++] = n;
            }
        }
        for (; i < nums.length; i++) {
            nums[i] = 0;
        }
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

    // 293
    public List<String> generatePossibleNextMoves(String s) {
        List list = new ArrayList();
        for (int i=-1; (i = s.indexOf("++", i+1)) >= 0; )
            list.add(s.substring(0, i) + "--" + s.substring(i+2));
        return list;
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


    // 366 find leaves on nodes
    public class findLeavesSolution {
        final List<List<Integer>> res = new ArrayList<>();
        public List<List<Integer>> findLeaves(TreeNode root) {
            findLeavesHelper(root);
            return res;
        }

        private int findLeavesHelper(TreeNode root) {
            if (root == null) return -1;
            final int level = 1 + Math.max(findLeavesHelper(root.left), findLeavesHelper(root.right));
            if (res.size() <= level) {
                res.add(new ArrayList<>());
            }
            res.get(level).add(root.val);
            return level;
        }
    }

    // 367 perfect square
    // sequence 1 + 3 + 5 + 7
    public boolean isPerfectSquare(int num) {
        if (num < 1) return false;
        for (int i = 1; num > 0; i += 2)
            num -= i;
        return num == 0;
    }

    // binary search
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

    // newton's method
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

    // 404 Sum of Left Leaves
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null) return 0;
        int ans = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while(!stack.empty()) {
            TreeNode node = stack.pop();
            if(node.left != null) {
                if (node.left.left == null && node.left.right == null)
                    ans += node.left.val;
                else
                    stack.push(node.left);
            }
            if(node.right != null) {
                if (node.right.left != null || node.right.right != null)
                    stack.push(node.right);
            }
        }
        return ans;
    }

    // 405 Num to Hex
    private char[] map = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

    public String toHex(int num) {
        if(num == 0) return "0";
        String result = "";
        while(num != 0){
            result = map[(num & 15)] + result;
            num = (num >>> 4);
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

    // 422 Valid Word Square
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
    public int countSegments(String s) {
        return ("x " + s).split(" +").length - 1;
    }

    // 455 Cookie
    public int findContentChildren(int[] g, int[] s) {
        if (s.length == 0 || g.length == 0) {
            return 0;
        }
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0;
        for (int j = 0; i < g.length && j < s.length; j++) {
            if (g[i] <= s[j]) {
                i++;
            }
        }
        return i;
    }

    // 448. Find All Numbers Disappeared in an Array
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            int val = Math.abs(nums[i]) - 1;
            if (nums[val] > 0) {
                nums[val] = -nums[val];
            }
        }
        for (int i = 0; i < nums.length; i++)
            if (nums[i] > 0)
                res.add(i + 1);
        return res;
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

    public int findComplement2(int num) {
        // highestOneBit(n) returns 2^m, where 2^m < n < 2^(m+1)
        return ~num & ((Integer.highestOneBit(num) << 1) - 1);
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

    // 485 Max Consecutive Ones
    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int maxSum = 0, curSum = 0;
        for (int i = 0; i < nums.length + 1; i++) {
            if (i < nums.length && nums[i] == 1) {
                curSum++;
            } else {
                maxSum = Math.max(curSum, maxSum);
                curSum = 0;
            }
        }
        return maxSum;
    }

    // 496 Next Greater Element
    /** Use a stack to store a decreasing subsequence, and pop all items that is smaller than the next item */
    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();  // store the integer and its next greater integer
        Stack<Integer> s = new Stack<Integer>();
        for (int n : nums) {
            while (!s.isEmpty() && s.peek() < n) {
                map.put(s.pop(), n);
            }
            s.push(n);
        }
        for (int i = 0; i < findNums.length; i++) {
            findNums[i] = map.getOrDefault(findNums[i], -1);
        }
        return findNums;
    }

    // 506
    public String[] findRelativeRanks(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new String[0];
        }
        int n = nums.length;
        String[] result = new String[n];
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; ++i) {
            map.put(nums[i], i);
        }
        Arrays.sort(nums);
        for (int i = 0; i < n / 2; ++i) {
            int temp = nums[i];
            nums[i] = nums[n - i - 1];
            nums[n - i - 1] = temp;
        }

        result[map.get(nums[0])] = "Gold Medal";
        if (1 < n) {
            result[map.get(nums[1])] = "Silver Medal";
        }
        if (2 < n) {
            result[map.get(nums[2])] = "Bronze Medal";
        }
        for (int j = 3; j < n; ++j) {
            result[map.get(nums[j])] = String.valueOf(j + 1);
        }
        return result;
    }

    // 520 Detect Capital
    public boolean detectCapitalUse(String word) {
        int numUpper = 0;
        for (int i = 0; i < word.length(); i++) {
            if (Character.isUpperCase(word.charAt(i))) {
                numUpper++;
            }
        }
        if (numUpper == 1) {
            return Character.isUpperCase(word.charAt(0));
        }
        return numUpper == 0 || numUpper == word.length();
    }



    // 557
    public String reverseWords(String s) {
        char[] ca = s.toCharArray();
        for (int i = 0; i < ca.length; i++) {
            if (ca[i] != ' ') {   // when i is a non-space
                int j = i;
                while (j + 1 < ca.length && ca[j + 1] != ' ') { j++; } // move j to the end of the word
                reverse(ca, i, j);
                i = j;
            }
        }
        return new String(ca);
    }

    private void reverse(char[] ca, int i, int j) {
        for (; i < j; i++, j--) {
            char tmp = ca[i];
            ca[i] = ca[j];
            ca[j] = tmp;
        }
    }

    // 561 Array Partition
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length; i += 2) {
            res += nums[i];
        }
        return res;
    }

    // 566 Matrix Reshape
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int x = nums.length;
        int y = nums[0].length;
        if (x * y != r * c) {
            return nums;
        }
        int[][] res = new int[r][c];
        for (int i = 0; i < r * c; i++) {
            res[i/c][i%c] = nums[i/y][i%y];
        }
        return res;
    }
}

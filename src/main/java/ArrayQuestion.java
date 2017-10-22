package main.java;

import java.util.*;

public class ArrayQuestion {
    // 26 Remove Duplicates
    public int removeDuplicates(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        int newLength = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[newLength++] = nums[i];
            }
        }
        return newLength;
    }

    // 35 binary search
    public int searchInsert(int[] A, int target) {
        int low = 0, high = A.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (A[mid] == target) {
                return mid;
            }
            if (A[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    // 88 Merge Sorted Arrays
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
    protected void merge2(int[] nums1, int m, int[] nums2, int n) {
        while (n > 0) {
            nums1[m + n - 1] = (m == 0 || nums2[n - 1] > nums1[m - 1]) ? nums2[--n] : nums1[--m];
        }
    }

    // 119 Pascal Triangle
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        res.add(1);
        for (int i = 1; i < rowIndex; i++) {
            for (int j = i - 1; j > 0; j--) {
                int temp = res.get(j - 1) + res.get(j);
                res.set(j, temp);
            }
            res.add(1);
        }
        return res;
    }

    // 136
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i : nums) {
            res ^= i;
        }
        return res;
    }

    // 152. Maximum Product Subarray
    int maxProduct(int[] A) {
        // store the result that is the max we have found so far
        int r = A[0];

        // imax/imin stores the max/min product of
        // subarray that ends with the current number A[i]
        for (int i = 1, imax = r, imin = r; i < A.length; i++) {
            // multiplied by a negative makes big number smaller, small number bigger
            // so we redefine the extremums by swapping them
            if (A[i] < 0) {
                int temp = imax;
                imax = imin;
                imin = temp;
            }
            // max/min product for the current number is either the current number itself
            // or the max/min by the previous number times the current one
            imax = Math.max(A[i], imax * A[i]);
            imin = Math.min(A[i], imin * A[i]);

            // the newly computed max value is a candidate for our global result
            r = Math.max(r, imax);
        }
        return r;
    }

    // 189
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    // 219
    /**
     * Given an array of integers and an integer k, find out whether there are two distinct indices
     * i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j
     * is at most k.
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            // sliding window
            if (i > k) {
                set.remove(nums[i - k - 1]);
            }
            if (!set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }

    // 238. Product of Array Except Self
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length, right = 1;
        int[] res = new int[len];
        res[0] = 1;
        for (int i = 1; i < len; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        for (int i = len - 1; i >= 0; i--) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }

    // 243 shorest word distance: given an array and two strings, find the minimum distance of the
    // two strings in that array
    public int shortestDistance(String[] words, String word1, String word2) {
        int i1 = -1, i2 = -1, res = Integer.MAX_VALUE;
        for (int i = 0, length = words.length; i < length; i++) {
            String s = words[i];
            if (s.equals(word1)) {
                i1 = i;
            } else if (s.equals(word2)) {
                i2 = i;
            }
            if (i1 != -1 && i2 != -1) {
                res = Math.min(res, Math.abs(i1 - i2));
            }
        }
        return res;
    }

    // 245. shortest word distance 3
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int p1 = -1, p2 = -1, res = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                if (word1.equals(word2)) {
                    p2 = p1;
                }
                p1 = i;
            } else if (words[i].equals(word2)) {
                p2 = i;
            }
            if (p1 != -1 && p2 != -1) {
                res = Math.min(res, Math.abs(p1-p2));
            }
        }
        return res;
    }

    // 260 2 single numbers
    public int[] singleNumbers(int[] nums) {
        // Pass 1 :
        // Get the XOR of the two numbers we need to find
        int diff = 0;
        for (int num : nums) {
            diff ^= num;
        }
        // Get its last set bit
        diff &= -diff;

        /** note the way to distinguish 2 numbers */
        int[] rets = {0, 0}; // this array stores the two numbers we will return
        for (int num : nums) {
            if ((num & diff) == 0) // the bit is not set
                rets[0] ^= num;
             else // the bit is set
                rets[1] ^= num;
        }
        return rets;
    }

    // 268 the Missing Number from 0 to n in a n-size array (Yext)
    public int missingNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res ^= nums[i];
            res ^= i;
        }
        res ^= nums.length;
        return res;
    }
    public int missingNumber2(int[] nums) {
        int sum = nums.length;
        for (int i = 0; i < nums.length; i++)
            sum += i - nums[i];
        return sum;
    }

    // 280
    /**
     * Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
     */
    public void wiggleSort(int[] nums) {
        // swap between 2 consecutive numbers
        // if odd index & prev > curr, swap
        // if even index & prev < curr, swap
        for (int i = 1; i < nums.length; i++) {
            int a = nums[i - 1];
            if ((i % 2 == 1) == (a > nums[i])) {
                nums[i - 1] = nums[i];
                nums[i] = a;
            }
        }
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

    // 287. Find the Duplicate Number (Yext Interview)
    // Method 1: divide-and-conquer
    public int findDuplicate(int[] nums) {
        int start = 1, end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            int count = 0;
            for (int n : nums) {
                if (n <= mid) {
                    count++;
                }
            }
            if (count <= mid) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }
    // Method 2: Linked list cycle 2
    public int findDuplicate2(int[] nums) {
        if (nums.length > 1) {
            int slow = nums[0], fast = nums[nums[0]];
            while (slow != fast) {
                slow = nums[slow];
                fast = nums[nums[fast]];
            }
            fast = 0;
            while(fast != slow) {
                fast = nums[fast];
                slow = nums[slow];
            }
            return slow;
        }
        return -1;
    }

    // 347
    public static List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer>[] buckets = new List[nums.length + 1];     // Bucket Sort
        HashMap<Integer, Integer> m = new HashMap<Integer, Integer>();
        for (int n : nums) {
            m.put(n, m.getOrDefault(n, 0) + 1);
        }
        for (int key : m.keySet()) {
            int freq = m.get(key);
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<Integer>();
            }
            buckets[freq].add(key);
        }
        List<Integer> res = new ArrayList<>();
        for (int pos = buckets.length - 1; pos >= 0 && res.size() < k; pos--) {
            if (buckets[pos] != null) {
                res.addAll(buckets[pos]);
            }
        }
        return res;
    }

    // 370
    public int[] getModifiedArray(int length, int[][] updates) {
        // add val at the start index and -val at the end index
        int res[] = new int[length];
        for (int[] update : updates) {
            res[update[0]] += update[2];
            int end = update[1];
            if (end < length - 1) {
                res[end + 1] -= update[2];
            }
        }
        for (int i = 1; i < length; i++) {
            res[i] += res[i - 1];
        }
        return res;
    }

    // 406 Queue Reconstruction by height
    public int[][] reconstructQueue(int[][] people) {
        /**
          pick up the tallest guy first
          when insert the next tall guy, just need to insert him into kth position
          repeat until all people are inserted into list
         */
        Arrays.sort(people,new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[0]!=o2[0]?-o1[0]+o2[0]:o1[1]-o2[1];
            }
        });
        List<int[]> res = new LinkedList<>();
        for(int[] cur : people){
            res.add(cur[1],cur);
        }
        return res.toArray(new int[people.length][]);
    }

    // 413: Arithmetic slices: return # of subarray that forms arithmetic sequence
    public int numberOfArithmeticSlices(int[] A) {
        if (A.length < 3) return 0;
        int res = 0, difference = Integer.MAX_VALUE, count = 0;
        for (int i = 1; i < A.length; i++) {
            int curDiff = A[i] - A[i-1];
            if (curDiff == difference) {
                count++;
                res += count; /** note the way to accumulate res */
            } else {
                count = 0;
                difference = curDiff;
            }
        }
        return res;
    }

    // 419: Battleships in a board
    public int countBattleships(char[][] board) {
        int res = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                char temp = board[i][j];
                /** only add the first X appeared */
                if (temp == 'X') res += ((i > 0 && board[i-1][j] == temp)||(j > 0 && board[i][j-1] == temp)) ? 0 : 1;
            }
        }
        return res;
    }

    // 442. Find All Duplicates in an Array
    /**
     * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
     * Find all the elements that appear twice in this array.
     */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; ++i) {
            int index = Math.abs(nums[i])-1;
            if (nums[index] < 0)
                res.add(Math.abs(index+1));
            nums[index] = -nums[index];
        }
        return res;
    }

    // 455 Cookie
    public int findContentChildren(int[] g, int[] s) {
        if (s.length == 0 || g.length == 0) return 0;
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

    // 447
    public int totalHammingDistance(int[] nums) {
        int total = 0, n = nums.length;
        for (int j = 0; j < 32; j++) {
            int bitCount = 0;
            for (int i = 0; i < n; i++) {
                bitCount += (nums[i] >> j) & 1;
            }
            total += bitCount * (n - bitCount);
        }
        return total;
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
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                res.add(i + 1);
            }
        }
        return res;
    }

    // 454 4Sum II
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                int sum = C[i] + D[j];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        int res = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                res += map.getOrDefault(-1 * (A[i] + B[j]), 0);
            }
        }

        return res;
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
    /**
     * Use a stack to store a decreasing subsequence, and pop all items that is smaller than the
     * next item
     */
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

    // 503
    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> s = new Stack<>();
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        for (int i = 0; i < 2 * n; i++) {
            int num = nums[i % n];
            while (!s.isEmpty() && nums[s.peek()] < num) {
                res[s.pop()] = num;
            }
            if (i < n) {
                s.push(i);
            }
        }
        return res;
    }

    // 531 Find lonely pixels

    /**
     * A black lonely pixel is character 'B' that located at a specific position where the same row
     * and same column don't have any other black pixels.
     */
    // record the number of Bs in each column & each row
    public int findLonelyPixel(char[][] picture) {
        int n = picture.length, m = picture[0].length;

        int[] rowCount = new int[n], colCount = new int[m];
        for (int i=0;i<n;i++)
            for (int j=0;j<m;j++)
                if (picture[i][j] == 'B') { rowCount[i]++; colCount[j]++; }

        int count = 0;
        for (int i=0;i<n;i++)
            for (int j=0;j<m;j++)
                if (picture[i][j] == 'B' && rowCount[i] == 1 && colCount[j] == 1) count++;

        return count;
    }

    // 560 Subarray Sum Equals k: Given an array and an integer k, find # of continuous subarrays whose sum equals to k.
    public int subarraySum(int[] nums, int k) {
        int result = 0, sum = 0;
        Map<Integer, Integer> preSum = new HashMap<Integer, Integer>();
        preSum.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (preSum.containsKey(sum - k)) {
                result += preSum.get(sum - k);
            }
            preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
        }

        return result;
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

    // 565 Array Nesting
    /**
     * S[K] = { A[K], A[A[K]], A[A[A[K]]], ... }; find the largest size of S[K]
     */
    public int arrayNesting(int[] nums) {
        int res = 1;
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int k = i; nums[k] >= 0; count++) {
                int ak = nums[k];
                nums[k] = -1; // mark a[k] as visited; next time don't step on it because it would be a smaller cycle
                k = ak;
            }
            res = Math.max(res, count);
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
            /**
             * note the indexing of matrices
             */
            res[i / c][i % c] = nums[i / y][i % y];
        }
        return res;
    }

    // 575. Distribution Candies
    /**
     * return the max kind of candies one can get
     */
    public int distributeCandies(int[] candies) {
        final Set<Integer> set = new HashSet<Integer>();
        for (int i : candies) {
            set.add(i);
            if (set.size() == candies.length / 2) {
                return candies.length / 2;
            }
        }
        return set.size();  // smaller than half
    }

    // 581 Shortest Unsorted Continuous Subarray
    public int findUnsortedSubarray(int[] A) {
        int len = A.length, start = -1, end = -2, min = A[len-1], max = A[0];
        for (int i = 1; i < len; i++) {
            min = Math.min(min, A[len - 1 - i]);
            max = Math.max(max, A[i]);
            if (A[i] < max) end = i; // if the index is not max, unsorted, move end to this index
            if (A[len - 1 - i] > min) start = len - 1 - i; // if the index is not min, unsorted, move start to this index
        }
        return end - start + 1;
    }

    // 598. Range Addition II
    public int maxCount(int m, int n, int[][] ops) {
        if (ops == null || ops.length == 0) {
            return m * n;
        }
        int row = Integer.MAX_VALUE, col = Integer.MAX_VALUE;
        for (int[] op : ops) {
            row = Math.min(row, op[0]);
            col = Math.min(col, op[1]);
        }

        return row * col;
    }

    // 605
    // place flowers s.t. no flowers are adjacent to each other
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        for (int i = 0; i < flowerbed.length && count < n; i++) {
            if (flowerbed[i] == 0) {
                //get next and prev flower bed slot values. If i lies at the ends the next and prev are considered as 0.
                int next = (i == flowerbed.length - 1) ? 0 : flowerbed[i + 1];
                int prev = (i == 0) ? 0 : flowerbed[i - 1];
                if (next == 0 && prev == 0) {
                    flowerbed[i] = 1;
                    count++;
                }
            }
        }

        return count == n;
    }

    /**
     * 611 valid triangle numbers
     * note the reduction of calculation of sums of 2 numbers
     */
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int count = 0, n = nums.length;
        for (int i=n-1;i>=2;i--) {
            int l = 0, r = i-1;
            while (l < r) {
                if (nums[l] + nums[r] > nums[i]) {
                    count += r-l;
                    r--;
                }
                else l++;
            }
        }
        return count;
    }

    // 621
    public int leastInterval(char[] tasks, int n) {
        int[] c = new int[26];
        for (char t : tasks) {
            c[t - 'A']++;
        }
        Arrays.sort(c);
        int i = 25;
        while (i >= 0 && c[i] == c[25]) {  // make most freq. numbers into frames with fixed order
            i--;
        }
        return Math.max(tasks.length, (c[25] - 1) * (n + 1) + 25 - i);
    }

    // 621 variation
    public static int taskScheduler(char[] tasks, int interval) {
        Map<Character, Integer> map = new HashMap<>();
        int res = 0;
        for (int i = 0; i < tasks.length; i++, res++) {
            while (map.containsKey(tasks[i])) {
                if (res - interval > map.get(tasks[i])) {
                    break;
                }
                res++;
            }
            map.put(tasks[i], res);
        }
        return res;
    }

    // 628. Maximum Product of Three Numbers
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        final int a = nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3];
        // there might be negative values
        final int b = nums[0] * nums[1] * nums[nums.length - 1];
        return a > b ? a : b;
    }

    // 645. Set Mismatch
    /** n size array with elements ranging from 1 to n, one element is duplicated & another element is missing; find these elements
     */
    public int[] findErrorNums(int[] nums) {
        int[] res = new int[2];
        for (int n : nums) {
            if (nums[Math.abs(n) - 1] < 0) {
                res[0] = Math.abs(n);
            } else {
                nums[Math.abs(n) - 1] *= -1;
            }
        }
        for (int i=0;i<nums.length;i++) {
            if (nums[i] > 0) res[1] = i+1;
        }
        return res;
    }

    public int[] findErrorNums2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int duplicate = 0, n = nums.length;
        long sum = (n * (n+1)) / 2;
        for(int i : nums) {
            if(set.contains(i)) duplicate = i;
            sum -= i;
            set.add(i);
        }
        return new int[] {duplicate, (int)sum + duplicate};
    }

    // 665 Check if it is possible to change one element to make the array non-descending
    // Greedy: try to change i-1 first; if can't, change i
    public boolean checkPossibility(int[] nums) {
        int count = 0;
        for (int i = 1; i < nums.length && count <= 1; i++) {
            if (nums[i - 1] > nums[i]) {
                count++;
                if (i - 1 > 0 && nums[i - 2] > nums[i]) {
                    nums[i] = nums[i-1];
                } else {
                    nums[i-1] = nums[i];
                }
            }
        }
        return count <= 1;
    }

    // 682
    public int calPoints(String[] ops) {
        int res = 0;
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < ops.length; i++) {
            if (ops[i].equals("C")) {
                if (i > 0) arr.remove(arr.size()-1);
            } else if (ops[i].matches("-?\\d+(\\.\\d+)?")) {
                arr.add(Integer.valueOf(ops[i]));
            } else if (ops[i].equals("D")) {
                arr.add(arr.get(arr.size()-1) * 2);
            } else arr.add(arr.get(arr.size()-1) + arr.get(arr.size()-2));
        }
        for (int i = 0; i < arr.size(); i++) {
            res += arr.get(i);
        }
        return res;
    }

    // 695
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int res = 0, row = grid.length, col = grid[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    res = Math.max(res, AreaOfIsland(grid, i, j));
                }
            }
        }
        return res;
    }
    // dfs
    private int AreaOfIsland(int[][] grid, int i, int j){
        if( i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == 1){
            grid[i][j] = 0;
            return 1 + AreaOfIsland(grid, i+1, j) + AreaOfIsland(grid, i-1, j) + AreaOfIsland(grid, i, j-1) + AreaOfIsland(grid, i, j+1);
        }
        return 0;
    }

    // 697
    public static int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> m = new HashMap<Integer, Integer>(), first = new HashMap<Integer, Integer>(), last = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (!first.containsKey(nums[i])) {
                first.put(nums[i], i);
            }
            last.put(nums[i], i);
            m.put(nums[i], m.getOrDefault(nums[i], 0) + 1);
        }
        int max = 0;
        HashSet<Integer> set = new HashSet<Integer>();
        for (int k : m.keySet()) {
            int v = m.get(k);
            if (v >= max) {
                if (v > max) {
                    set.clear();
                    max = v;
                }
                set.add(k);
            }
        }
        int res = Integer.MAX_VALUE;
        Iterator<Integer> it = set.iterator();
        while (it.hasNext()) {
            int temp = it.next();
            res = Math.min(res, last.get(temp) - first.get(temp));
        }
        return res + 1;
    }
}

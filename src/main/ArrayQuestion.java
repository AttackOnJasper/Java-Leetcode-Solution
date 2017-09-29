package main;

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

    // 39. Combination Sum 1
    /** Each number can be used infinite number of times */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<Integer>();
        dfsHelper(candidates, target, 0, path, res);
        return res;
    }

    private void dfsHelper(int[] candidates, int target, int start, List<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList(path));
            return;
        }
        if (target < 0) return;
        for (int i = start; i < candidates.length; i++) {
            path.add(candidates[i]);
            dfsHelper(candidates, target - candidates[i], i, path, res); /** can re-use i element */
            path.remove(path.size() - 1);
        }
    }

    // 40. Combination Sum 2
    /** each number can only be used once */
    public List<List<Integer>> combinationSum2(int[] cand, int target) {
        Arrays.sort(cand);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<Integer>();
        dfs(cand, 0, target, path, res);
        return res;
    }

    private void dfs(int[] cand, int cur, int target, List<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList(path));  // otherwise each list would be empty
            return;
        }
        if (target < 0) {
            return;
        }
        for (int i = cur; i < cand.length; i++) {
            if (i > cur && cand[i] == cand[i - 1]) {
                continue;
            }
            path.add(path.size(), cand[i]);
            dfs(cand, i + 1, target - cand[i], path, res);
            path.remove(path.size() - 1);
        }
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

    // Divide-and-Conquer
    public int maxSubArray2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
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

    // 78 Subsets
    public List<List<Integer>> subsets(int[] nums) {  // backtracking
        final List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
        list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.length; i++){
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
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

    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, newIndex = m + n - 1;
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


    // 119
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

    // 121 Max profit from one buy & sell
    /**
     * Kadane's Algorithm: the logic is to calculate the difference (maxCur += prices[i] - prices[i-1])
     * of the original array, and find a contiguous subarray giving maximum profit.
     * If the difference falls below 0, reset it to zero.
     */
    public int maxProfit(int[] prices) {
        int maxCur = 0, maxSoFar = 0;
        for(int i = 1; i < prices.length; i++) {
            maxCur = Math.max(0, maxCur + prices[i] - prices[i-1]);
            maxSoFar = Math.max(maxCur, maxSoFar);
        }
        return maxSoFar;
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

    // 216 Combination Sum
    /**
     * Find all possible combinations of k numbers that add up to a number n,
     * given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        combination(res, new ArrayList<>(), k, 1, n);
        return res;
    }

    private void combination(List<List<Integer>> ans, List<Integer> comb, int k,  int start, int n) {
        if (comb.size() > k) {
            return;
        }
        if (comb.size() == k && n == 0) {
            List<Integer> li = new ArrayList<>(comb);
            ans.add(li);
            return;
        }
        for (int i = start; i <= n && i<=9; i++) {
            comb.add(i);
            combination(ans, comb, k, i+1, n-i);
            comb.remove(comb.size() - 1);
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

    // shortest word distance 3
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

        // Pass 2 :
        int[] rets = {0, 0}; // this array stores the two numbers we will return
        for (int num : nums) {
            if ((num & diff) == 0) // the bit is not set
            {
                rets[0] ^= num;
            } else // the bit is set
            {
                rets[1] ^= num;
            }
        }
        return rets;
    }

    // 268 the Missing Number from 0 to n in a n-size array
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
     * A black lonely pixel is character 'B' that located at a specific position where the same row and same column don't have any other black pixels.
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

    // 560 Subarray Sum Equals k
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
            res[i / c][i % c] = nums[i / y][i % y];
        }
        return res;
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

    // 665
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
}

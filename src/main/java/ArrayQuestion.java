package main.java;

import java.util.*;

public class ArrayQuestion {
    // 1
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                result[1] = i;
                result[0] = map.get(target - nums[i]);
                return result;
            }
            map.put(nums[i], i);
        }
        return result;
    }

    // 33. Search in rotated sorted arry e.g. 5 6 7 1 2 3 4
    /** Idea: find minimum then find target */

    // 34. Search for range
    /** do one binary search for lower bound and one for upper bound */
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) return new int[]{-1, -1};
        int low = 0, high = nums.length - 1;
        // left
        while (low < high) {
            int mid = (low + high) / 2;
            if (nums[mid] >= target) high = mid;
            else low = mid + 1;
        }
        if (nums[low] != target) return new int[]{-1, -1};
        int left = low;
        // right
        low = 0;
        high = nums.length - 1;
        while (low < high) {
            int mid = (low + high) / 2 + 1; /** note the modification of mid to avoid 'low' stuck on the same value every time */
            if (nums[mid] > target) high = mid - 1;
            else low = mid;
        }
        return new int[]{left, low};
    }

    // 59 Spiral Matrix II
    /** Idea: Generate matrix in increasing number order; generate a circle in each while loop */

    // 66 Plus one
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1 ; i >= 0; i--) {
            if (digits[i] != 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        int[] newNumber = new int[digits.length+1];
        newNumber[0] = 1;
        return newNumber;
    }

    // 73. Set Matrix Zeroes: Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
    /** Idea: use first index of a col or row to keep track if the col or row should be converted to 0 */
    public void setZeroes(int[][] matrix) {
        int col0 = 1, m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) col0 = 0;
            for (int j = 0; j < n; j++)
                if (matrix[i][j] == 0)
                    matrix[i][0] = matrix[0][j] = 0;
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 1; j--)
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            if (col0 == 0) matrix[i][0] = 0;
        }
    }

    // 74. Search a 2D Matrix
    /** treat it as a sorted list */

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
        while (n > 0)
            nums1[m + n - 1] = (m == 0 || nums2[n - 1] > nums1[m - 1]) ? nums2[--n] : nums1[--m];
    }

    // 75 sort array of 0, 1, 2
    /** Use low and high to keep track of index of 0 and 2 to be inserted */
    public void sortColors(int[] nums) {
        if(nums==null || nums.length<2) return;
        int low = 0;
        int high = nums.length-1;
        for(int i = low; i<=high;) {
            if(nums[i]==0) {
                // swap A[i] and A[low] and i,low both ++
                int temp = nums[i];
                nums[i] = nums[low];
                nums[low]=temp;
                i++;
                low++;
            }else if(nums[i]==2) {
                //swap A[i] and A[high] and high--;
                int temp = nums[i];
                nums[i] = nums[high];
                nums[high]=temp;
                high--;
            }else {
                i++;
            }
        }
    }

    // 118 Generate pascal triangle for first n rows
    public List<List<Integer>> generatePascal(int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> row = new ArrayList<>();
        for( int i = 0; i < n; i++) {
            /** reverse the index to ensure previous value is not mutated yet */
            for(int j = row.size() - 1; j > 0; j--) // changing from 2nd element to (n-1)th element
                row.set(j, row.get(j)+row.get(j-1));
            row.add(1);
            res.add(new ArrayList<>(row));
        }
        return res;
    }

    // 119 Pascal Triangle
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        res.add(1);
        for (int i = 1; i < rowIndex; i++) {
            for (int j = i - 1; j > 0; j--)
                res.set(j, res.get(j - 1) + res.get(j));
            res.add(1);
        }
        return res;
    }

    // 136
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i : nums)
            res ^= i;
        return res;
    }

    // 152. Maximum Product Subarray
    public int maxProduct(int[] A) {
        int r = A[0];

        // imax/imin stores the max/min product of subarray that ends with the current number A[i]
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

    // 219 Given an array of integers and an integer k, find out whether there are two distinct indices
    // i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j
    // is at most k.
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            // sliding window
            if (i > k) {
                set.remove(nums[i - k - 1]);
            }
            if (!set.add(nums[i])) { /** no explicit comparison */
                return true;
            }
        }
        return false;
    }

    // 229. Majority Element II: find all elements that occur more than n / 3 times
    /** similar to majority element I */
    public List<Integer> majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<Integer>();
        List<Integer> result = new ArrayList<Integer>();
        int number1 = nums[0], number2 = nums[0], count1 = 0, count2 = 0, len = nums.length;
        /** compute count of the 2 numbers, which are guaranteed to the 2 numbers if they occur more than n / 3 times */
        for (int i = 0; i < len; i++) {
            if (nums[i] == number1)
                count1++;
            else if (nums[i] == number2)
                count2++;
            else if (count1 == 0) {
                number1 = nums[i];
                count1 = 1;
            } else if (count2 == 0) {
                number2 = nums[i];
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }
        /** check if the 2 numbers occur more than n / 3 times */
        count1 = 0;
        count2 = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] == number1)
                count1++;
            else if (nums[i] == number2)
                count2++;
        }
        if (count1 > len / 3)
            result.add(number1);
        if (count2 > len / 3)
            result.add(number2);
        return result;
    }

    // 238. Product of Array Except Self
    /** Keep track of left sub-product & right sub-product */
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        res[0] = 1;
        for (int i = 1; i < nums.length; i++)
            res[i] = res[i - 1] * nums[i - 1];
        for (int i = nums.length - 1, right = 1; i >= 0; i--) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }

    // 243 shortest word distance: given an array and two strings, find the minimum distance of the
    // two strings in that array
    public int shortestDistance(String[] words, String word1, String word2) {
        int i1 = -1, i2 = -1, res = Integer.MAX_VALUE;
        for (int i = 0, length = words.length; i < length; i++) {
            String s = words[i];
            if (s.equals(word1)) {
                i1 = i;
            } else if (s.equals(word2))
                i2 = i;
            if (i1 != -1 && i2 != -1)
                res = Math.min(res, Math.abs(i1 - i2));
        }
        return res;
    }

    // 244. shortest word distance 2: use a class to optimize the method if it's called multiple times with the same word list
    class WordDistance {
        Map<String, List<Integer>> map;
        public WordDistance(String[] words) {
            map = new HashMap<>();
            for (int i = 0; i < words.length; i++) {
                List<Integer> temp = map.getOrDefault(words[i], new ArrayList<>());
                temp.add(i);
                map.put(words[i], temp);
            }
        }

        public int shortest(String word1, String word2) {
            List<Integer> list1 = map.get(word1), list2 = map.get(word2);
            int res = Integer.MAX_VALUE;
            for (int i = 0, j = 0; i < list1.size() && j < list2.size();) {
                int index1 = list1.get(i), index2 = list2.get(j);
                if (index1 > index2) {
                    res = Math.min(res, index1 - index2);
                    j++;
                } else {
                    res = Math.min(res, index2 - index1);
                    i++;
                }
            }
            return res;
        }
    }

    // 245. shortest word distance 3: word1 can be the same as word2
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int p1 = -1, p2 = -1, res = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                if (word1.equals(word2))
                    p2 = p1;
                p1 = i;
            } else if (words[i].equals(word2))
                p2 = i;
            if (p1 != -1 && p2 != -1)
                res = Math.min(res, Math.abs(p1-p2));
        }
        return res;
    }

    // 260 2 single numbers: find the distinct 2 single numbers in a list
    public int[] singleNumbers(int[] nums) {
        // Pass 1 :
        // Get the XOR of the two numbers we need to find
        int diff = 0;
        for (int num : nums) {
            diff ^= num;
        }
        /** Get its last set bit (2 different numbers
         * -> at least one 1 digit in XOR result
         * -> only one number contribute to this 1
         * -> divide all numbers into one group with this bit set and one with the bit not set) */
        diff &= -diff;

        int[] res = {0, 0}; // this array stores the two numbers we will return
        for (int num : nums) {
            if ((num & diff) == 0)
                res[0] ^= num;
            else // the bit is set
                res[1] ^= num;
        }
        return res;
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

    // 280 Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
    public void wiggleSort(int[] nums) {
        /**
         *  swap between 2 consecutive numbers
         *  if odd index & prev > curr, swap
         *  if even index & prev < curr, swap
         */
        for (int i = 1; i < nums.length; i++) {
            int a = nums[i - 1];
            if ((i % 2 == 1) == (a > nums[i])) {
                nums[i - 1] = nums[i];
                nums[i] = a;
            }
        }
    }

    // 287. Find the Duplicate Number (Yext Interview)
    /** Method 1: divide-and-conquer: compare median to nums[mid] */
    public int findDuplicate(int[] nums) {
        int start = 1, end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            int count = 0;
            for (int n : nums)
                if (n <= mid)
                    count++;
            if (count <= mid) start = mid + 1;
            else end = mid;
        }
        return start;
    }
    /** Method 2: Linked list cycle 2 */
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
    /** Method 3: Negating the index of array (if array can be modified) */

    // 347 return k most frequent numbers
    /** Bucket Sort */
    public static List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer>[] buckets = new List[nums.length + 1];  // buckets[i] store the numbers that occur i times in the original list
        HashMap<Integer, Integer> m = new HashMap<Integer, Integer>();  // map num to frequency
        for (int n : nums)
            m.put(n, m.getOrDefault(n, 0) + 1);
        for (int key : m.keySet()) {
            int freq = m.get(key);
            if (buckets[freq] == null)
                buckets[freq] = new ArrayList<Integer>();
            buckets[freq].add(key);
        }
        List<Integer> res = new ArrayList<>();
        for (int pos = buckets.length - 1; pos >= 0 && res.size() < k; pos--)
            if (buckets[pos] != null)
                res.addAll(buckets[pos]);
        return res;
    }

    // 370. Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments
    // each element of subarray A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc.
    public int[] getModifiedArray(int length, int[][] updates) {
        // add val at the start index and -val at the end index
        int res[] = new int[length];
        for (int[] update : updates) {
            res[update[0]] += update[2];
            int end = update[1];
            if (end < length - 1)
                res[end + 1] -= update[2];
        }
        for (int i = 1; i < length; i++)
            res[i] += res[i - 1];
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
        for(int[] cur : people)
            res.add(cur[1],cur);
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

    // 442. Find All Duplicates in an Array (yext)
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
     * next item to put in the next greater element map
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
    /** store all the presums and occurrences */
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

    // 575. Distribution Candies: return the max kind of candies one can get
    /**
     * Use set to return number of distinct numbers
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
            if (A[i] < max) end = i; // if the index is not the current max -> unsorted -> move end to this index
            if (A[len - 1 - i] > min) start = len - 1 - i; // if the index is not the current min -> unsorted -> move start to this index
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
    /** greedy */
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
    /** Greedy: try to decrease i-1 first; if can't, increase i */
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

    // 683
    // flowers[i] = x means the flower at pos x would be blooming on day i. Output the day where
    // there are at least 2 flowers blooming, and the number of flowers between them is k, and those flowers are not blooming
    public int kEmptySlots(int[] flowers, int k) {
        int[] days = new int[flowers.length]; // days[i] record blooming day of flower in pos i+1
        for (int i = 0; i < flowers.length; i++) {
            days[flowers[i] - 1] = i + 1;
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0, left = 0, right = k + 1; right < flowers.length; i++) {
            /** need to make sure days[i] > days[left] && days[i] > days[right] for i in left + 1 ... right - 1 */
            if (days[i] < days[left] || days[i] <= days[right]) {
                if (i == right) res = Math.min(res, Math.max(days[left], days[right])); // valid case
                left = i;
                right = k + 1 + i;
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    // 683 Variation
    // flowers[i] = x means the flower at pos x would be blooming on day i. Output the day where
    // there are k consecutive flowers blooming
    public int kEmptySlotsII(int[] P, int K) {
        if (K == P.length) return K;
        int[] days = new int[P.length]; // days[i] record blooming day of flower in pos i+1
        for (int i = 0; i < P.length; i++) days[P[i] - 1] = i + 1;
        int res = Integer.MIN_VALUE;
        for (int i = 0, left = -1, right = K; right <= P.length; i++) {
            if (right == P.length) { // edge case
                if (i == P.length - 1 && days[i] < days[left]) return Math.max(res, days[left] - 1);
                if (days[i] > days[left]) break;
            } else if (left == -1) { // edge case: do not need to compare left at the start
                if (days[i] >= days[right]) {
                    if (i == right) res = Math.max(res, days[right] - 1);
                    left = i;
                    right = K + i + 1;
                }
            } else if (days[i] > days[left] || days[i] >= days[right]) { // need to make sure blooming days from left + 1 to right - 1 are less than bloomingDays[left] and bloomingDays[right]
                if (i == right) res = Math.max(res, Math.min(days[left], days[right]) - 1); // valid case
                left = i;
                right = K + i + 1;
            }
        }
        return res == Integer.MIN_VALUE ? -1 : res;
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
        if(i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == 1) {
            grid[i][j] = 0;
            return 1 + AreaOfIsland(grid, i+1, j) + AreaOfIsland(grid, i-1, j) + AreaOfIsland(grid, i, j-1) + AreaOfIsland(grid, i, j+1);
        }
        return 0;
    }

    // 697 find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums
    public static int findShortestSubArray(int[] nums) {
        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])){
                map.put(nums[i], new int[]{1, i, i});  // the first element in array is degree, second is first index of this key, third is last index of this key
            } else {
                int[] temp = map.get(nums[i]);
                temp[0]++;
                temp[2] = i;
            }
        }
        int res = Integer.MAX_VALUE, degree = 0;
        for (int[] value : map.values()) {
            if (value[0] > degree) {
                degree = value[0];
                res = value[2] - value[1] + 1;
            } else if (value[0] == degree){
                res = Math.min(value[2] - value[1] + 1, res);
            }
        }
        return res;
    }

    // 717
    /** one bit '0', 2 bit '10' or '11' */
    public boolean isOneBitCharacter(int[] bits) {
        int n = bits.length, i = 0;
        while (i < n - 1) {
            if (bits[i] == 0) i++;
            else i += 2;
        }
        return i == n - 1;
    }

    // 720
    public String longestWord(String[] words) {
        if (words == null || words.length == 0) return "";
        Arrays.sort(words);
        int max = 0;
        String res = words[0];
        Set<String> set = new HashSet<String>();
        for (String word : words) {
            set.add(word);
            int i = 1, len = word.length();
            for (; i < len; i++) {
                if (!set.contains(word.substring(0,i))) {
                    break;
                }
            }
            if (i == len && len > max) {
                max = len;
                res = word;
            }
        }
        return res;
    }

    // 724 Find Pivot Index
    /** find index where LHS sum == RHS sum */
    public int pivotIndex(int[] nums) {
        int sum = 0, half = 0;
        for (int n : nums) sum += n;

        for (int i = 0; i < nums.length; i++) {
            if (sum == half * 2 + nums[i]) return i;
            half += nums[i];
        }
        return -1;
    }

    // 739
    public int[] dailyTemperatures(int[] temperatures) {
        if (temperatures.length == 0) return new int[0];
        int[] res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            for (int j = i + 1; j < temperatures.length; j++) {
                if (temperatures[i] < temperatures[j]) {
                    res[i] = j;
                    break;
                }
            }
        }
        return res;
    }

    // 769. Max Chunks To Make Sorted I
    public int maxChunksToSorted(int[] arr) {
        int curLen = 0, res = 0;
        for (int i = 0; i < arr.length; i++) {
            curLen = Math.max(curLen, arr[i]);
            if (i == curLen) res++;
        }
        return res;
    }

    // 775. Global and Local Inversions
    public boolean isIdealPermutation(int[] A) {
        for (int i = 0; i < A.length; i++) {
            if (Math.abs(A[i] - i) > 1) return false;
        }
        return true;
    }
}

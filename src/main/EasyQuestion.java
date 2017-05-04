package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class EasyQuestion {
    // 104
    public int maxDepth(TreeNode root) {
        return root == null? 0 : 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    // 136
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i : nums) {
            res ^= i;
        }
        return res;
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

    // 216
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode newLeft = invertTree(root.right);
        TreeNode newRight = invertTree(root.left);
        root.left = newLeft;
        root.right = newRight;
        return root;
    }


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
    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.empty() && nums[i] > stack.peek()) {
                stack.pop();
            }
            map.put(nums[i], (stack.empty()) ? -1 : stack.peek());
            stack.push(nums[i]);
        }
        for (int i = 0; i < findNums.length; i++) {
            findNums[i] = map.get(findNums[i]);
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

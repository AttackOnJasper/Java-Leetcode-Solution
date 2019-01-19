package main.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BackTrackQuestion {
    // 17 Letter Combinations of a Phone Number
    /** every time use queue to poll, format, and push */
    public List<String> letterCombinations(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        if (digits == null || digits.length() == 0) return ans;
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        for(int i =0; i<digits.length();i++){
            int x = Character.getNumericValue(digits.charAt(i));
            while(ans.peek().length()==i){
                String t = ans.remove();
                for(char s : mapping[x].toCharArray())
                    ans.add(t+s);
            }
        }
        return ans;
    }

    // 22 Generate Parentheses: get combinations of n pairs of parentheses
    public List<String> generateParentheses(int n) {
        List<String> res = new ArrayList<>();
        generateParenthesesHelper("", res, 0, 0, n);
        return res;
    }
    private void generateParenthesesHelper(String cur, List<String> res, int left, int right, int max) {
        if (cur.length() == max * 2) {
            res.add(cur);
            return;
        }
        if (left > right) {
            generateParenthesesHelper(cur + ')', res, left, right + 1, max);
        }
        if (left < max) {
            generateParenthesesHelper(cur + '(', res, left + 1, right, max);
        }
    }

    // 39 Combination Sum I
    /** Each number can be used infinite number of times */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        combinationSumHelper(0, new ArrayList<>(), res, candidates, target);
        return res;
    }
    private void combinationSumHelper(
        int start,
        List<Integer> temp,
        List<List<Integer>> res,
        int[] candidates,
        int target
    ) {
        if (target == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        if (target < 0) return;
        for (int i = start; i < candidates.length; i++) {
            temp.add(candidates[i]);
            /** use i as start to re-use same element */
            combinationSumHelper(i, temp, res, candidates, target - candidates[i]);
            temp.remove(temp.size() - 1);
        }
    }

    // 40 Combination Sum: each element can only be used once, no duplicated combinations
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        combinationSum2Helper(res, candidates, target, new ArrayList<>(), 0);
        return res;
    }
    private void combinationSum2Helper(List<List<Integer>> res, int[] candidates, int target, List<Integer> temp, int start) {
        if (target <= 0) {
            if (target == 0)
                res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (i == start || candidates[i] != candidates[i - 1]) {
                temp.add(candidates[i]);
                combinationSum2Helper(res, candidates, target - candidates[i], temp, i + 1);
                temp.remove(temp.size() - 1);
            }
        }
    }

    // 46 Permutation: return permutations on distinct set
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        permuteHelper(res, nums, new ArrayList<>());
        return res;
    }
    private void permuteHelper(List<List<Integer>> res, int[] nums, List<Integer> cur) {
        if (cur.size() == nums.length) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(cur.contains(nums[i])) continue;
            cur.add(nums[i]);
            permuteHelper(res, nums, cur);
            cur.remove(cur.size() - 1);
        }
    }

    // 77 Combination
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        combineHelper(res, new ArrayList<>(), n, k, 1);
        return res;
    }
    private void combineHelper(List<List<Integer>> res, List<Integer> temp, int n, int k, int index) {
        if (temp.size() == k) {
            res.add(new ArrayList<>(temp));
        } else {
            for (int i = index; i < n + 1; i++) {
                if (!temp.contains(i)) {
                    temp.add(i);
                    combineHelper(res, temp, n, k, i);
                    temp.remove(temp.size() - 1);
                }
            }
        }
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

    // 90 Subset II
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        subsetsWithDupHelper(nums, res, new ArrayList<>(), 0);
        return res;
    }
    private void subsetsWithDupHelper(int[] nums, List<List<Integer>> res, List<Integer> temp, int start) {
        res.add(new ArrayList<>(temp));
        for (int i = start; i < nums.length; i++) {
            if (i == start || nums[i] != nums[i - 1]) {
                temp.add(nums[i]);
                subsetsWithDupHelper(nums, res, temp, i+1);
                temp.remove(temp.size() - 1);
            }
        }
    }

    // 131 Palindrome partitioning (Uber interview)
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        partitionHelper(s, res, new ArrayList<>(), 0);
        return res;
    }
    private void partitionHelper(String s, List<List<String>> res, List<String> temp, int start) {
        if (temp.size() > 0 && start >= s.length()) {
            res.add(new ArrayList<>(temp));
        }
        for (int i = start; i < s.length(); i++) {
            if (isPalindrome(s, start, i)) {
                temp.add(s.substring(start, i + 1));
                partitionHelper(s, res, temp, i + 1);
                temp.remove(temp.size() - 1);
            }
        }
    }
    private boolean isPalindrome(String str, int l, int r){
        if(l==r) return true;
        while(l<r){
            if(str.charAt(l)!=str.charAt(r)) return false;
            l++;r--;
        }
        return true;
    }

    // 216 Combination Sum
    /**
     * Find all possible combinations of k numbers that add up to a number n,
     * given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
     */
    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        combination(res, new ArrayList<>(), k, 1, n);
        return res;
    }
    private static void combination(List<List<Integer>> ans, List<Integer> comb, int k,  int start, int n) {
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

    // 401. Binary Watch
    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        int[] nums1 = new int[]{8, 4, 2, 1}, nums2 = new int[]{32, 16, 8, 4, 2, 1};
        for(int i = 0; i <= num; i++) {
            List<Integer> list1 = generateDigit(nums1, i);
            List<Integer> list2 = generateDigit(nums2, num - i);
            for(int num1: list1) {
                if(num1 >= 12) continue;
                for(int num2: list2) {
                    if(num2 >= 60) continue;
                    res.add(num1 + ":" + (num2 < 10 ? "0" + num2 : num2));
                }
            }
        }
        return res;
    }
    private List<Integer> generateDigit(int[] nums, int count) {
        List<Integer> res = new ArrayList<>();
        generateDigitHelper(nums, count, 0, 0, res);
        return res;
    }
    private void generateDigitHelper(int[] nums, int count, int pos, int sum, List<Integer> res) {
        if(count == 0) {
            res.add(sum);
            return;
        }
        for(int i = pos; i < nums.length; i++)
            generateDigitHelper(nums, count - 1, i + 1, sum + nums[i], res);
    }

    // 526 Beatiful arrangement: arrange the array of 1 .. N numbers s.t. num[i] % i == 0 || i % num[i] == 0
    /**
     * note the addition and deletion of middle element
     */
    int count = 0;
    public int countArrangement(int N) {
        if (N == 0) return 0;
        countArrangementHelper(N, 1, new int[N + 1]);
        return count;
    }
    private void countArrangementHelper(int N, int pos, int[] used) {
        if (pos > N) {
            count++;
            return;
        }
        for (int i = 1; i <= N; i++) {
            if (used[i] == 0 && (i % pos == 0 || pos % i == 0)) {
                used[i] = 1;
                countArrangementHelper(N, pos + 1, used);
                used[i] = 0;
            }
        }
    }

    // 784 Letter Case Permutation
    public List<String> letterCasePermutation(String S) {
        List<String> res = new LinkedList<>();
        helper(res, S.toCharArray(), 0);
        return res;
    }
    private void helper(List<String> res, char[] arr, int index) {
        if (index == arr.length) {
            res.add(new String(arr));
        } else {
            if (Character.isLetter(arr[index])) {
                arr[index] = Character.toUpperCase(arr[index]);
                helper(res, arr, index + 1);
                arr[index] = Character.toLowerCase(arr[index]);
            }
            helper(res, arr, index + 1);
        }
    }
}

package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BackTrackQuestion {
    // 39 Combination Sum
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
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
}

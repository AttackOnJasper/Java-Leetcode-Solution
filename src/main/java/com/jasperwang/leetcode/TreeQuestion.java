package com.jasperwang.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * Tree-focused LeetCode solutions and related utilities.
 *
 * <p>The methods are grouped by problem domain rather than by difficulty. Most implementations are
 * self-contained so they can be copied into individual LeetCode submissions.
 */
public class TreeQuestion {

    public static class TreeNode {

        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int x) {
            val = x;
        }
    }

    /**
     * bfs.
     *
     * @param root input value
     */
    public void bfs(TreeNode root) {
        Queue<TreeNode> q = new ArrayDeque<TreeNode>();
        HashSet<Integer> visited = new HashSet<>();
        q.add(root);
        while (!q.isEmpty()) {
            root = q.poll();
            System.out.print(root.val);
            if (!visited.contains(root.val)) {
                visited.add(root.val);
                if (root.left != null) {
                    q.add(root.left);
                }
                if (root.right != null) {
                    q.add(root.right);
                }
            }
        }
    }

    /**
     * LeetCode 144: preorder Traversal: val -> left -> right.
     *
     * @param root input value
     * @return result
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                res.add(root.val);
                root = root.left;
            } else {
                root = stack.pop();
                root = root.right;
            }
        }
        return res;
    }

    /**
     * preorder traversal2.
     *
     * @param root input value
     * @return result
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> s = new Stack<TreeNode>();
        while (root != null || !s.isEmpty()) {
            while (root != null) {
                res.add(root.val);
                s.push(root);
                root = root.left;
            }
            root = s.pop();
            root = root.right;
        }
        return res;
    }

    /**
     * preorder traversal3.
     *
     * @param root input value
     * @return result
     */
    public List<Integer> preorderTraversal3(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.add(root);
        while (!s.isEmpty()) {
            root = s.pop();
            if (root != null) {
                res.add(root.val);
                s.push(root.right);
                s.push(root.left);
            }
        }
        return res;
    }

    /**
     * LeetCode 145: postorder Traversal.
     * <p>
     * push right & set right to null, push left & set left to null.
     *
     * @param root input value
     * @return result
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);
        while (!s.isEmpty()) {
            root = s.peek();
            if (root.left == null && root.right == null) {
                s.pop();
                res.add(root.val);
            } else {
                if (root.right != null) {
                    s.push(root.right);
                    root.right = null;
                }
                if (root.left != null) {
                    s.push(root.left);
                    root.left = null;
                }
            }
        }
        return res;
    }

    /**
     * postorder traversal2.
     *
     * @param root input value
     * @return result
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                res.addFirst(root.val);
                root = root.right;
            } else {
                root = stack.pop();
                root = root.left;
            }
        }
        return res;
    }

    /**
     * LeetCode 94: inorder Traversal.
     *
     * @param root input value
     * @return result
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> s = new Stack<TreeNode>();
        while (root != null || !s.isEmpty()) {
            while (root != null) {
                s.push(root);
                root = root.left;
            }
            root = s.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }

    /**
     * inorder traversal2.
     *
     * @param root input value
     * @return result
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }

    // return the minimum path sum

    /**
     * watch out that only one child is null does not mean it's end of path.
     *
     * @param root input value
     * @return result
     */
    public int minPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null) {
            return minPathSum(root.right) + root.val;
        }
        if (root.right == null) {
            return minPathSum(root.left) + root.val;
        }
        return Math.min(minPathSum(root.left), minPathSum(root.right)) + root.val;
    }

    /**
     * LeetCode 98: is BST.
     * <p>
     * in order and then compare prev and curr.
     *
     * @param root input value
     * @return result
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                if (pre != null && root.val <= pre.val) {
                    return false;
                }
                pre = root;
                root = root.right;
            }
        }
        return true;
    }

    /**
     * LeetCode 100: is same tree.
     *
     * @param p input value
     * @param q input value
     * @return result
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return p == q;
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * LeetCode 101: is symmetric.
     *
     * @param root input value
     * @return result
     */
    public boolean isSymmetric(TreeNode root) {
        return root == null || isSymmetricHelper(root.left, root.right);
    }

    private boolean isSymmetricHelper(TreeNode left, TreeNode right) {
        /** note the reduction of null cases */
        if (left == null || right == null) {
            return left == right;
        }
        if (left.val != right.val) {
            return false;
        }
        return isSymmetricHelper(left.left, right.right) && isSymmetricHelper(right.left, left.right);
    }

    /**
     * LeetCode 103: binary Tree Zigzag Level Order Traversal.
     * <p>
     * bfs and add data with different orders by using a flag.
     *
     * @param root input value
     * @return result
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        LinkedList<TreeNode> q = new LinkedList<TreeNode>();
        if (root == null) {
            return res;
        }
        q.add(root);
        boolean flag = true;
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> temp = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                root = q.poll();
                if (flag) {
                    temp.add(root.val);
                } else {
                    temp.add(0, root.val);
                }
                if (root.left != null) {
                    q.offer(root.left);
                }
                if (root.right != null) {
                    q.offer(root.right);
                }
            }
            flag = !flag;
            res.add(new LinkedList<>(temp));
        }
        return res;
    }

    /**
     * LeetCode 104: max depth.
     *
     * @param root input value
     * @return result
     */
    public int maxDepth(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    private int pInorder; // index of inorder array
    private int pPreorder; // index of preorder array

    /**
     * LeetCode 105: constructs a binary tree from preorder and inorder traversals.
     *
     * @param preorder input value
     * @param inorder  input value
     * @return result
     */
    public TreeNode buildTreeFromInorderAndPreorder(int[] preorder, int[] inorder) {
        pPreorder = pInorder = 0;
        return buildTreeFromInorderAndPreorderHelper(preorder, inorder, null);
    }

    private TreeNode buildTreeFromInorderAndPreorderHelper(
        int[] preorder, int[] inorder, TreeNode start) {
        if (pPreorder >= preorder.length) {
            return null;
        }
        // root node
        final TreeNode root = new TreeNode(preorder[pPreorder++]);
        // left
        if (inorder[pInorder] != root.val) {
            root.left = buildTreeFromInorderAndPreorderHelper(preorder, inorder, root);
        }
        pInorder++;
        // right
        if (start == null || inorder[pInorder] != start.val) {
            root.right = buildTreeFromInorderAndPreorderHelper(preorder, inorder, start);
        }
        return root;
    }

    /**
     * LeetCode 106: construct Binary Tree from Inorder and Postorder Traversal.
     * <p>
     * Idea: take the last element in postorder array as the root, find the position of the root in the inorder array;
     * then locate the range for left sub-tree and right sub-tree and do recursion.
     */
    private int pPostorder; // index of postorder array

    /**
     * build tree.
     *
     * @param inorder   input value
     * @param postorder input value
     * @return result
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        pInorder = inorder.length - 1;
        pPostorder = postorder.length - 1;
        return buildTree(inorder, postorder, null);
    }

    private TreeNode buildTree(int[] inorder, int[] postorder, TreeNode end) {
        if (pPostorder < 0) {
            return null;
        }
        // create root node
        final TreeNode root = new TreeNode(postorder[pPostorder--]);

        // if right node exist, create right subtree
        if (inorder[pInorder] != root.val) {
            root.right = buildTree(inorder, postorder, root);
        }
        pInorder--;

        // if left node exist, create left subtree
        if ((end == null) || (inorder[pInorder] != end.val)) {
            root.left = buildTree(inorder, postorder, end);
        }
        return root;
    }

    /**
     * LeetCode 107: level Order Traversal II.
     *
     * @param root input value
     * @return result
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        levelOrderBottomHelper(res, 0, root);
        return res;
    }

    private void levelOrderBottomHelper(List<List<Integer>> res, int level, TreeNode root) {
        if (root == null) {
            return;
        }
        if (res.size() <= level) {
            res.add(0, new LinkedList<>());
        }
        levelOrderBottomHelper(res, level + 1, root.right);
        levelOrderBottomHelper(res, level + 1, root.left);
        res.get(res.size() - level - 1).add(root.val);
    }

    /**
     * level order bottom2.
     *
     * @param root input value
     * @return result
     */
    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        LinkedList<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> temp = new LinkedList<Integer>();
            for (int i = 0; i < size; i++) {
                root = q.pop();
                temp.add(root.val);
                if (root.left != null) {
                    q.offer(root.left);
                }
                if (root.right != null) {
                    q.offer(root.right);
                }
            }
            res.addFirst(new LinkedList<>(temp));
        }
        return res;
    }

    /**
     * LeetCode 108: sorted Array to BST.
     *
     * @param num input value
     * @return result
     */
    public TreeNode sortedArrayToBST(int[] num) {
        if (num.length == 0) {
            return null;
        }
        return sortedArrayToBSTHelper(num, 0, num.length - 1);
    }

    /**
     * watch out the use of low & high in params to avoid extra space used because of splitting array
     */
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

    /**
     * LeetCode 110.
     * <p>
     * watch out the reduction of recursion here.
     *
     * @param root input value
     * @return result
     */
    public boolean isBalanced(TreeNode root) {
        return isBalancedHelper(root) != -1;
    }

    private int isBalancedHelper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = isBalancedHelper(root.left);
        if (left == -1) {
            return -1;
        }
        int right = isBalancedHelper(root.right);
        if (right == -1) {
            return -1;
        }
        if (Math.abs(left - right) > 1) {
            return -1;
        }
        return 1 + Math.max(left, right);
    }

    /**
     * LeetCode 111.
     * <p>
     * pay attention to the case where either left or right is null.
     *
     * @param root input value
     * @return result
     */
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

    /**
     * LeetCode 114: flatten a binary tree in pre-order in place (arrange all nodes to the right of previous one).
     * <p>
     * post order traversal: build the tree from bottom up (last element to root).
     *
     * @param root input value
     */
    public void flatten(TreeNode root) {
        flattenHelper(root, null);
    }

    private TreeNode flattenHelper(TreeNode root, TreeNode pre) {
        if (root == null) {
            return pre;
        }
        pre = flattenHelper(root.right, pre);
        pre = flattenHelper(root.left, pre);
        root.right = pre;
        root.left = null;
        return root;
    }

    /**
     * LeetCode 156: binary Tree Upside Down.
     *
     * @param root input value
     * @return result
     */
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null) {
            return root;
        }
        TreeNode newRoot = upsideDownBinaryTree(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        return newRoot;
    }

    /**
     * upside down binary tree2.
     *
     * @param root input value
     * @return result
     */
    public TreeNode upsideDownBinaryTree2(TreeNode root) {
        TreeNode curr = root;
        TreeNode next;
        TreeNode temp = null;
        TreeNode prev = null;

        while (curr != null) {
            next = curr.left;
            curr.left = temp;
            temp = curr.right;
            curr.right = prev;

            prev = curr;
            curr = next;
        }
        return prev;
    }

    /**
     * LeetCode 226: invert BST.
     * <p>
     * note the use of temp TreeNode to hold the original right.
     *
     * @param root input value
     * @return result
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode tempRight = root.right;
        root.right = invertTree(root.left);
        root.left = invertTree(tempRight);
        return root;
    }

    /**
     * dfs.
     *
     * @param root input value
     * @return result
     */
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return null;
        }
        final Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            final TreeNode node = stack.pop();
            final TreeNode left = node.left;
            node.left = node.right;
            node.right = left;
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return root;
    }

    /**
     * LeetCode 235: lowest Common Ancestor.
     * <p>
     * just walk down from the whole tree's root as long as both p and q are in the same subtree (meaning their values
     * are both smaller or both larger than root's).
     *
     * @param root input value
     * @param p    input value
     * @param q    input value
     * @return result
     */
    public TreeNode lowestCommonAncestorForBST(TreeNode root, TreeNode p, TreeNode q) {
        while ((root.val - p.val) * (root.val - q.val) > 0) {
            root = p.val < root.val ? root.left : root.right;
        }
        return root;
    }

    /**
     * LeetCode 236: lowest Common Ancestor in a binary tree.
     *
     * @param root input value
     * @param p    input value
     * @param q    input value
     * @return result
     */
    public TreeNode lowestCommonAncestorForBinaryTree(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        final TreeNode left = lowestCommonAncestorForBinaryTree(root.left, p, q);
        final TreeNode right = lowestCommonAncestorForBinaryTree(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }

    int res = 0;

    /**
     * LeetCode 250: counts univalue subtrees.
     *
     * @param root input value
     * @return result
     */
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) {
            return 0;
        }
        countUnivalSubtreesHelper(root, root.val);
        return res;
    }

    private boolean countUnivalSubtreesHelper(TreeNode root, int val) {
        if (root == null) {
            return true;
        }
        // use | here for explicit comparison
        if (!countUnivalSubtreesHelper(root.left, root.val)
            | !countUnivalSubtreesHelper(root.right, root.val)) {
            return false;
        }
        res++;
        return root.val == val;
    }

    /**
     * LeetCode 257: binary Tree Paths: return all root-to-leaf paths.
     *
     * @param root input value
     * @return result
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        binaryTreePathsHelper(root, "", res);
        return res;
    }

    private void binaryTreePathsHelper(TreeNode root, String curString, List<String> res) {
        if (root.left == null && root.right == null) {
            res.add(curString + root.val);
        }
        if (root.left != null) {
            binaryTreePathsHelper(root.left, curString + root.val + "->", res);
        }
        if (root.right != null) {
            binaryTreePathsHelper(root.right, curString + root.val + "->", res);
        }
    }

    /**
     * LeetCode 270: closest Binary Search Tree Value.
     *
     * @param root   input value
     * @param target input value
     * @return result
     */
    public int closestValue(TreeNode root, double target) {
        int a = root.val;
        TreeNode child = (a < target) ? root.right : root.left;
        if (child == null) {
            return a;
        }
        int b = closestValue(child, target);
        return Math.abs(a - target) < Math.abs(b - target) ? a : b;
    }

    private static int ZERO = 0;

    /**
     * LeetCode 298: finds the longest consecutive path in a binary tree.
     *
     * @param root input value
     * @return result
     */
    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        longestConsecutiveHelper(root, 0, root.val);
        return ZERO;
    }

    private void longestConsecutiveHelper(TreeNode root, int cur, int target) {
        if (root == null) {
            return;
        }
        cur = (root.val == target) ? cur + 1 : 1;
        ZERO = Math.max(cur, ZERO);
        longestConsecutiveHelper(root.left, cur, root.val + 1);
        longestConsecutiveHelper(root.right, cur, root.val + 1);
    }

    /**
     * LeetCode 366: groups leaves by removal round.
     */
    public class findLeavesSolution {

        final List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> findLeaves(TreeNode root) {
            findLeavesHelper(root);
            return res;
        }

        private int findLeavesHelper(TreeNode root) {
            if (root == null) {
                return -1;
            }
            final int level = 1 + Math.max(findLeavesHelper(root.left), findLeavesHelper(root.right));
            if (res.size() <= level) {
                res.add(new ArrayList<>());
            }
            res.get(level).add(root.val);
            return level;
        }
    }

    /**
     * LeetCode 404: sum of Left Leaves.
     *
     * @param root input value
     * @return result
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int ans = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.empty()) {
            TreeNode node = stack.pop();
            if (node.left != null) {
                if (node.left.left == null && node.left.right == null) {
                    ans += node.left.val;
                } else {
                    stack.push(node.left);
                }
            }
            if (node.right != null) {
                if (node.right.left != null || node.right.right != null) {
                    stack.push(node.right);
                }
            }
        }
        return ans;
    }

    /**
     * LeetCode 437: counts downward paths whose values sum to the target.
     *
     * <p>Tip: store prefix sums and their counts, then find matches by subtracting earlier prefix
     * sums from the current sum.
     *
     * @param root input value
     * @param sum  input value
     * @return result
     */
    public int pathSum(TreeNode root, int sum) {
        HashMap<Integer, Integer> preSum = new HashMap();
        preSum.put(0, 1);
        return pathSumHelper(root, 0, sum, preSum);
    }

    private int pathSumHelper(
        TreeNode root, int currSum, int target, HashMap<Integer, Integer> preSum) {
        if (root == null) {
            return 0;
        }
        currSum += root.val;
        int res =
            preSum.getOrDefault(
                currSum - target,
                0); // find if the difference between currSum & one of presum is target
        preSum.put(currSum, preSum.getOrDefault(currSum, 0) + 1);

        res +=
            pathSumHelper(root.left, currSum, target, preSum)
                + pathSumHelper(root.right, currSum, target, preSum);
        preSum.put(currSum, preSum.get(currSum) - 1);
        return res;
    }

    /**
     * recursion.
     *
     * @param root input value
     * @param sum  input value
     * @return result
     */
    public int pathSum2(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        return pathSumFrom(root, sum) + pathSum2(root.left, sum) + pathSum2(root.right, sum);
    }

    private int pathSumFrom(TreeNode node, int sum) {
        if (node == null) {
            return 0;
        }
        return (node.val == sum ? 1 : 0)
            + pathSumFrom(node.left, sum - node.val)
            + pathSumFrom(node.right, sum - node.val);
    }

    private Integer prevVal = null;
    private int count = 1;
    private int modeCount = 0;

    /**
     * LeetCode 501: finds mode values in a BST that may contain duplicates.
     *
     * @param root input value
     * @return result
     */
    public int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        List<Integer> list = new ArrayList<>();
        traverse(root, list);
        return list.stream().mapToInt(i -> i).toArray();
    }

    private void traverse(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        traverse(root.left, list);
        if (prevVal != null) {
            if (root.val == prevVal) {
                count++;
            } else {
                count = 1;
            }
        }
        if (count > modeCount) {
            modeCount = count;
            list.clear();
            list.add(root.val);
        } else if (count == modeCount) {
            list.add(root.val);
        }
        prevVal = root.val;
        traverse(root.right, list);
    }

    /**
     * LeetCode 513: find Bottom Left Tree Value.
     * <p>
     * note the order of left & right.
     *
     * @param root input value
     * @return result
     */
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root.right != null) {
                queue.add(root.right);
            }
            if (root.left != null) {
                queue.add(root.left);
            }
        }
        return root.val;
    }

    /**
     * LeetCode 617: merge two trees.
     *
     * @param t1 input value
     * @param t2 input value
     * @return result
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        TreeNode res = new TreeNode(t1.val + t2.val);
        res.left = mergeTrees(t1.left, t2.left);
        res.right = mergeTrees(t1.right, t2.right);
        return res;
    }

    /**
     * LeetCode 637: average of each level.
     *
     * @param root input value
     * @return result
     */
    public List<Double> averageOfLevels(final TreeNode root) {
        List<Double> res = new ArrayList<>();
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            final int n = q.size();
            double sum = 0;
            for (int i = 0; i < n; i++) {
                TreeNode cur = q.poll();
                sum += cur.val;
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
            res.add(sum / n);
        }
        return res;
    }

    /**
     * LeetCode 653: two Sum IV - Input is a BST.
     * <p>
     * method 1 For each node, we check if k - node.val exists in this BST.
     *
     * @param root input value
     * @param k    input value
     * @return result
     */
    public boolean findTarget(TreeNode root, int k) {
        return dfs(root, root, k);
    }

    private boolean dfs(TreeNode root, TreeNode cur, int k) {
        if (cur == null) {
            return false;
        }
        return search(root, cur, k - cur.val) || dfs(root, cur.left, k) || dfs(root, cur.right, k);
    }

    private boolean search(TreeNode root, TreeNode cur, int value) {
        if (root == null) {
            return false;
        }
        return (root.val == value) && (root != cur)
            || (root.val < value) && search(root.right, cur, value)
            || (root.val > value) && search(root.left, cur, value);
    }

    /**
     * method 2 Use hashtable to store the values.
     *
     * @param root input value
     * @param k    input value
     * @return result
     */
    public boolean findTarget2(TreeNode root, int k) {
        HashSet<Integer> set = new HashSet<>();
        return dfs(root, set, k);
    }

    private boolean dfs(TreeNode root, HashSet<Integer> set, int k) {
        if (root == null) {
            return false;
        }
        if (set.contains(k - root.val)) {
            return true;
        }
        set.add(root.val);
        return dfs(root.left, set, k) || dfs(root.right, set, k);
    }

    /**
     * LeetCode 671: find second minimum value.
     *
     * @param root input value
     * @return result
     */
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null || root.left == null) {
            return -1;
        }
        final int left =
            (root.left.val == root.val) ? findSecondMinimumValue(root.left) : root.left.val;
        final int right =
            (root.right.val == root.val) ? findSecondMinimumValue(root.right) : root.right.val;
        if (left == -1) {
            return right;
        }
        if (right == -1) {
            return left;
        }
        return Math.min(left, right);
    }

    /**
     * LeetCode 687: longest Univalue Path.
     *
     * @param root input value
     * @return result
     */
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        longestUnivaluePathHelper(root, root.val);
        return ZERO;
    }

    private int longestUnivaluePathHelper(TreeNode root, int parentVal) {
        if (root == null) {
            return 0;
        }
        /** left, right: max path length in which all nodes' val == parentVal */
        int left = longestUnivaluePathHelper(root.left, root.val);
        int right = longestUnivaluePathHelper(root.right, root.val);
        ZERO = Math.max(ZERO, left + right);
        return parentVal == root.val ? Math.max(left, right) + 1 : 0;
    }

    // binary tree to double linked list
    private TreeNode head, prev = null;

    /**
     * binary tree to double linked list.
     *
     * @param root input value
     * @return result
     */
    public TreeNode binaryTreeToDoubleLinkedList(TreeNode root) {
        if (root == null) {
            return null;
        }
        helper(root);
        return head;
    }

    private void helper(TreeNode root) {
        if (root == null) {
            return;
        }
        helper(root.left);
        if (prev == null) {
            head = root;
        } else {
            root.left = prev;
            prev.right = root;
        }
        prev = root;
        helper(root.right);
    }

    /**
     * LeetCode 776: split bst.
     *
     * @param root input value
     * @param V    input value
     * @return result
     */
    public TreeNode[] splitBST(TreeNode root, int V) {
        TreeNode sP = new TreeNode(0), bP = new TreeNode(0);
        split(root, V, sP, bP);
        return new TreeNode[]{sP.right, bP.left};
    }

    private void split(TreeNode node, int v, TreeNode sP, TreeNode bP) {
        if (node == null) {
            return;
        }
        if (node.val <= v) {
            sP.right = node;
            TreeNode right = node.right;
            node.right = null;
            split(right, v, node, bP);
        } else {
            bP.left = node;
            TreeNode left = node.left;
            node.left = null;
            split(left, v, sP, node);
        }
    }

    /**
     * LeetCode 582: kill process.
     *
     * @param pid  input value
     * @param ppid input value
     * @param kill input value
     * @return result
     */
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, List<Integer>> map = new HashMap<>(); // store parent children relationship
        for (int i = 0; i < pid.size(); ++i) {
            map.putIfAbsent(ppid.get(i), new ArrayList<>());
            map.get(ppid.get(i)).add(pid.get(i));
        }
        List<Integer> ans = new ArrayList<>();
        Queue<Integer> q = new ArrayDeque<>();
        q.add(kill);
        while (!q.isEmpty()) {
            int n = q.poll();
            ans.add(n);
            if (map.containsKey(n)) {
                q.addAll(map.get(n));
            }
        }
        return ans;
    }
}

package main;

import java.util.*;

public class TreeQuestion {
    public void bfs(TreeNode root) {
        Queue<TreeNode> q = new ArrayDeque<TreeNode>();
        HashSet<Integer> visited = new HashSet<>();
        q.add(root);
        while (!q.isEmpty()) {
            root = q.poll();
            System.out.print(root.val);
            if (!visited.contains(root.val)) {
                visited.add(root.val);
                q.add(root.left);
                q.add(root.right);
            }
        }
    }

    /** push right & set right to null, push left & set left to null */
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

    // 94
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

    // 114
    public List<Integer> preorderTraversal(TreeNode root) {
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
    // dfs
    public List<Integer> preorderTraversal2(TreeNode root) {
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


    // 156 Binary Tree Upside Down
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if(root == null || root.left == null) return root;
        TreeNode newRoot = upsideDownBinaryTree(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        return newRoot;
    }

    public TreeNode upsideDownBinaryTree2(TreeNode root) {
        TreeNode curr = root;
        TreeNode next = null;
        TreeNode temp = null;
        TreeNode prev = null;

        while(curr != null) {
            next = curr.left;

            // swapping nodes now, need temp to keep the previous right child
            curr.left = temp;
            temp = curr.right;
            curr.right = prev;

            prev = curr;
            curr = next;
        }
        return prev;
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

    // 270 Closest Binary Search Tree Value
    public int closestValue(TreeNode root, double target) {
        int a = root.val;
        TreeNode child = (a < target) ? root.right : root.left;
        if (child == null) return a;
        int b = closestValue(child, target);
        return Math.abs(a - target) < Math.abs(b - target) ? a : b;
    }

    // 298
    private static int max = 0;
    public static int longestConsecutive(TreeNode root) {
        if(root == null) return 0;
        longestConsecutiveHelper(root, 0, root.val);
        return max;
    }

    private static void longestConsecutiveHelper(TreeNode root, int cur, int target){
        if(root == null) return;
        cur = (root.val == target)? cur + 1 : 1;
        max = Math.max(cur, max);
        longestConsecutiveHelper(root.left, cur, root.val + 1);
        longestConsecutiveHelper(root.right, cur, root.val + 1);
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

}

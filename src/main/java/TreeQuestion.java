package main.java;

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
                if (root.left != null) q.add(root.left);
                if (root.right != null) q.add(root.right);
            }
        }
    }

    // 144 Preorder Traversal
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
    // dfs
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

    // 145 Postorder Traversal
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
    public List<Integer> postorderTraversal2(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) return res;
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

    // 94 Inorder Traversal
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
    public int minPathSum(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null) return minPathSum(root.right) + root.val;
        if (root.right == null) return minPathSum(root.left) + root.val;
        return Math.min(minPathSum(root.left), minPathSum(root.right)) + root.val;
    }

    // 98 is BST
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                if(pre != null && root.val <= pre.val) return false;
                pre = root;
                root = root.right;
            }
        }
        return true;
    }

    // 100
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) return p == q;
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
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

    // 103 Binary Tree Zigzag Level Order Traversal
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        LinkedList<TreeNode> q = new LinkedList<TreeNode>();
        if (root == null) return res;
        q.add(root);
        boolean flag = true;
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> temp = new LinkedList<>();
            for(int i = 0; i < size; i++){
                root = q.poll();
                if(flag){
                    temp.add(root.val);
                }else{
                    temp.add(0, root.val);
                }
                if(root.left != null) q.offer(root.left);
                if(root.right != null) q.offer(root.right);
            }
            flag = !flag;
            res.add(new LinkedList<>(temp));
        }
        return res;
    }

    // 104
    public int maxDepth(TreeNode root) {
        return root == null? 0 : 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    // 106 Construct Binary Tree from Inorder and Postorder Traversal
    /**
     * The the basic idea is to take the last element in postorder array as the root,
     * find the position of the root in the inorder array; then locate the range for left sub-tree
     * and right sub-tree and do recursion. Use a HashMap to record the index of root in the inorder array.
     */
    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length)
            return null;
        HashMap<Integer, Integer> hm = new HashMap<Integer,Integer>();
        for (int i=0;i<inorder.length;++i)
            hm.put(inorder[i], i);
        return buildTreePostIn(inorder, 0, inorder.length-1, postorder, 0,
            postorder.length-1,hm);
    }
    private static TreeNode buildTreePostIn(
        int[] inorder,
        int is,
        int ie,
        int[] postorder,
        int ps,
        int pe,
        HashMap<Integer,Integer> hm
    ){
        if (ps>pe || is>ie) return null;
        TreeNode root = new TreeNode(postorder[pe]);
        int ri = hm.get(postorder[pe]);
        TreeNode leftchild = buildTreePostIn(inorder, is, ri-1, postorder, ps, ps+ri-is-1, hm);
        TreeNode rightchild = buildTreePostIn(inorder,ri+1, ie, postorder, ps+ri-is, pe-1, hm);
        root.left = leftchild;
        root.right = rightchild;
        return root;
    }

    // 108 Sorted Array to BST
    public TreeNode sortedArrayToBST(int[] num) {
        if (num.length == 0) return null;
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

    // 110
    /**
     * watch out the reduction of recursion here
     */
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
        TreeNode next;
        TreeNode temp = null;
        TreeNode prev = null;

        while(curr != null) {
            next = curr.left;
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

    // 257. Binary Tree Paths: return all root-to-leaf paths
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        binaryTreePathsHelper(root, "", res);
        return res;
    }
    private void binaryTreePathsHelper(TreeNode root, String curString, List<String> res) {
        if (root.left == null && root.right == null) res.add(curString + root.val);
        if (root.left != null) binaryTreePathsHelper(root.left, curString + root.val + "->", res);
        if (root.right != null) binaryTreePathsHelper(root.right, curString + root.val + "->", res);
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

    /**
     * 437 Path Sum:
     * return the number of paths from up to down whose values sum up to target (sum)
     */
    public int pathSum(TreeNode root, int sum) {
        HashMap<Integer, Integer> preSum = new HashMap();
        preSum.put(0,1);
        return pathSumHelper(root, 0, sum, preSum);
    }
    private int pathSumHelper(TreeNode root, int currSum, int target, HashMap<Integer, Integer> preSum) {
        if (root == null) return 0;
        currSum += root.val;
        int res = preSum.getOrDefault(currSum - target, 0);  // find if the difference between currSum & one of presum is target
        preSum.put(currSum, preSum.getOrDefault(currSum, 0) + 1);

        res += pathSumHelper(root.left, currSum, target, preSum) + pathSumHelper(root.right, currSum, target, preSum);
        preSum.put(currSum, preSum.get(currSum) - 1);
        return res;
    }

    public int pathSum2(TreeNode root, int sum) {
        if (root == null) return 0;
        return pathSumFrom(root, sum) + pathSum2(root.left, sum) + pathSum2(root.right, sum);
    }
    private int pathSumFrom(TreeNode node, int sum) {
        if (node == null) return 0;
        return (node.val == sum ? 1 : 0)
            + pathSumFrom(node.left, sum - node.val) + pathSumFrom(node.right, sum - node.val);
    }

    // 501 find mode(s) in duplicated BST
    private Integer prevVal = null;
    private int count = 1;
    private int modeCount = 0;
    public int[] findMode(TreeNode root) {
        if (root == null) return new int[0];
        List<Integer> list = new ArrayList<>();
        traverse(root, list);
        return list.stream().mapToInt(i -> i).toArray();
    }

    private void traverse(TreeNode root, List<Integer> list) {
        if (root == null) return;
        traverse(root.left, list);
        if (prevVal != null) {
            if (root.val == prevVal) count++;
            else count = 1;
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

    // 513 Find Bottom Left Tree Value
    /**
     * note the order of left & right
     */
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root.right != null)
                queue.add(root.right);
            if (root.left != null)
                queue.add(root.left);
        }
        return root.val;
    }

    // 617 Merge two trees
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        TreeNode res = new TreeNode(t1.val + t2.val);
        res.left = mergeTrees(t1.left, t2.left);
        res.right = mergeTrees(t1.right, t2.right);
        return res;
    }

    // 637 average of each level
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
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
            res.add(sum / n);
        }
        return res;
    }

    // 653 Two Sum IV - Input is a BST
    /** Method 1
     *  Use in-order traversal to store input as an array -> two pointers
     */
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> l = new ArrayList<>();
        inorder(root, l);
        int start = 0, end = l.size() - 1;
        while (start < end) {
            if (l.get(start) + l.get(end) == k) {
                return true;
            }
            if (l.get(start) + l.get(end) < k) {
                start++;
            } else {
                end--;
            }
        }
        return false;
    }
    private void inorder(TreeNode root, List<Integer> l) {
        if (root == null) return;
        inorder(root.left, l);
        l.add(root.val);
        inorder(root.right, l);
    }

    /** Method 2
     * Use hashtable to store the values
     */
    public boolean findTarget2(TreeNode root, int k) {
        HashSet<Integer> set = new HashSet<>();
        return dfs(root, set, k);
    }
    private boolean dfs(TreeNode root, HashSet<Integer> set, int k){
        if(root == null)return false;
        if(set.contains(k - root.val))return true;
        set.add(root.val);
        return dfs(root.left, set, k) || dfs(root.right, set, k);
    }

    // 671
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null || root.left == null) return -1;
        int left = root.left.val, right = root.right.val;
        if (left == root.val) left = findSecondMinimumValue(root.left);
        if (right == root.val) right = findSecondMinimumValue(root.right);
        if (left == -1) return right;
        if (right == -1) return left;
        return Math.min(left, right);
    }

    // 687. Longest Univalue Path
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) return 0;
        helper(root);
        return max;
    }
    private int helper(TreeNode root, int parentVal) {
        if (root == null) return 0;
        int left = helper(root.left, root.val);
        int right = helper(root.right, root.val);
        max = Math.max(max, left + right);
        return parentVal == root.val ? Math.max(left, right) : 0;
    }

    // binary tree to double linked list
    private TreeNode head, prev = null;
    public TreeNode binaryTreeToDoubleLinkedList(TreeNode root) {
        if (root == null) return null;
        helper(root);
        return head;
    }
    private void helper(TreeNode root) {
        if (root == null) return;
        helper(root.left);
        if (prev == null)
            head = root;
        else {
            root.left = prev;
            prev.right = root;
        }
        prev = root;
        helper(root.right);
    }

    // 776
    public TreeNode[] splitBST(TreeNode root, int V) {
        TreeNode sP = new TreeNode(0), bP = new TreeNode(0);
        split(root, V, sP, bP);
        return new TreeNode[]{sP.right, bP.left};
    }
    private void split(TreeNode node, int v, TreeNode sP, TreeNode bP) {
        if (node == null) return;
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
}

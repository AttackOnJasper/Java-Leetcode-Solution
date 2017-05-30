package main;

import java.util.*;


public class MediumQuestion {
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

    // 2. Add Two Numbers Linked List
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        ListNode temp = res;
        int sum = 0;
        while (l1 != null || l2 != null) {
            sum /= 10;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            temp.next = new ListNode(sum % 10);
            temp = temp.next;
        }
        if (sum / 10 == 1)
            temp.next = new ListNode(1);
        return res.next;
    }

    // 3


    // 5. Longest Palindromic Substring


    // 6

    // 11. Container With Most Water

    // 12. Integer to Roman




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


    // 148. Sort List

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


    // 238. Product of Array Except Self



    // 245 Shortest Word Distance III
    public static int shortestWordDistance(String[] words, String word1, String word2) {
        int i = 0, index1 = -1, index2 = -1, res = Integer.MAX_VALUE;
        boolean flag = word1.equals(word2);
        while (i < words.length) {
            if (words[i].equals(word1)) {
                if (flag) {
                    index2 = index1;
                }
                index1 = i;
            } else if (words[i].equals(word2)) {
                index2 = i;
            }
            if (index1 != -1 && index2 != -1) {
                res = Math.min(res, Math.abs(index1 - index2));
            }
            i++;
        }
        return res;
    }

    // 246 Same after rotatign 180 degrees
    public boolean isStrobogrammatic(String num) {
        HashMap<Character, Character> h = new HashMap<>();
        h.put('8','8');
        h.put('1','1');
        h.put('6','9');
        h.put('9','6');
        h.put('0', '0');

        int l = 0, r = num.length() - 1;
        while (l <= r) {
            if (!h.containsKey(num.charAt(l))) return false;
            if (h.get(num.charAt(l)) != num.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }

    // 250 Count Univalue Subtrees
    int res = 0;

    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) return 0;
        countUnivalSubtreesHelper(root, root.val);
        return res;
    }

    private boolean countUnivalSubtreesHelper(TreeNode root, int val) {
        if (root == null) return true;
        if (!countUnivalSubtreesHelper(root.left, root.val) | !countUnivalSubtreesHelper(root.right, root.val)) return false;
        res++;
        return root.val == val;
    }

    // 280
    public void wiggleSort(int[] nums) {
        // swap between 2 consecutive numbers
        // if odd index & prev > curr, swap
        // if even index & prev < curr, swap
        for (int i=1; i<nums.length; i++) {
            int a = nums[i-1];
            if ((i%2 == 1) == (a > nums[i])) {
                nums[i-1] = nums[i];
                nums[i] = a;
            }
        }
    }

    // 294 Flip Game II
    /** decides if the first player can guarantee a win
     *  Idea 1: recursion on sub-case
     *  Idea 2: Dynamic programming
     * */
    public boolean canWin(String s) {
        if(s == null || s.length() < 2) return false;

        Set<String> winSet = new HashSet<String>();
        return canWin(s, winSet);
    }

    private boolean canWin(String s, Set<String> winSet){
        if(winSet.contains(s)) return true;

        for(int i = 0; i < s.length() - 1; i++) {
            if(s.charAt(i) == '+' && s.charAt(i + 1) == '+') {

                String sOpponent = s.substring(0, i) + "--" + s.substring(i + 2);
                if(!canWin(sOpponent, winSet)) {
                    winSet.add(s);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean canWin2(String s) {
        s = s.replace('-', ' ');
        int G = 0;
        List<Integer> g = new ArrayList<>();
        for (String t : s.split("[ ]+")) {
            int p = t.length();
            if (p == 0) continue;
            while (g.size() <= p) {
                char[] x = t.toCharArray();
                int i = 0, j = g.size() - 2;
                while (i <= j)
                    x[g.get(i++) ^ g.get(j--)] = '-';
                g.add(new String(x).indexOf('+'));
            }
            G ^= g.get(p);
        }
        return G != 0;
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


    // 311 Sparse Matrix Multiplication
    public int[][] multiply(int[][] A, int[][] B) {
        int rowA = A.length;
        if (rowA == 0) return null;
        int colA = A[0].length;
        int colB = B[0].length;
        int[][] res = new int[rowA][colB];
        for (int i = 0; i < rowA; i++)
            for (int k = 0; k < colA; k++)
                if (A[i][k] != 0)
                    for (int j = 0; j < colB; j++)
                        if (B[k][j] != 0)
                            res[i][j] += A[i][k] * B[k][j];
        return res;
    }


    // 323 Number of Connected Components in an Undirected Graph
    /** Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2 */
    public int countComponents(int n, int[][] edges) {
        int[] roots = new int[n];
        for(int i = 0; i < n; i++) roots[i] = i;

        for(int[] e : edges) {
            int root1 = find(roots, e[0]);
            int root2 = find(roots, e[1]);
            if(root1 != root2) {
                roots[root1] = root2;  // union
                n--;
            }
        }
        return n;
    }

    private int find(int[] roots, int id) {
        while(roots[id] != id) {
            roots[id] = roots[roots[id]];  // optional: path compression
            id = roots[id];
        }
        return id;
    }

    // 338
    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        for (int i=1; i<=num; i++) res[i] = res[i >> 1] + (i & 1);
        return res;
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

    // 362
    public class HitCounter {
        private int[] times;
        private int[] hits;
        /** Initialize your data structure here. */
        public HitCounter() {
            times = new int[300];
            hits = new int[300];
        }

        /** Record a hit.
         @param timestamp - The current timestamp (in seconds granularity). */
        public void hit(int timestamp) {
            int index = timestamp % 300;
            if (times[index] != timestamp) {
                times[index] = timestamp;
                hits[index] = 1;
            } else {
                hits[index]++;
            }
        }

        /** Return the number of hits in the past 5 minutes.
         @param timestamp - The current timestamp (in seconds granularity). */
        public int getHits(int timestamp) {
            int total = 0;
            for (int i = 0; i < 300; i++) {
                if (timestamp - times[i] < 300) {
                    total += hits[i];
                }
            }
            return total;
        }
    }


    // 364
    /** Instead of multiplying by depth, add integers multiple times (by going level by level and adding the unweighted sum to the weighted sum after each level) */
    // Input [[1, 1], 2, [1, 1]] -> output 8 (4 * 1 + 2 * 2)
//    public int depthSumInverse(List<NestedInteger> nestedList) {
//        int unweighted = 0, weighted = 0;
//        while (!nestedList.isEmpty()) {
//            List<NestedInteger> nextLevel = new ArrayList<>();
//            for (NestedInteger ni : nestedList) {
//                if (ni.isInteger())
//                    unweighted += ni.getInteger();
//                else
//                    nextLevel.addAll(ni.getList());
//            }
//            weighted += unweighted;
//            nestedList = nextLevel;
//        }
//        return weighted;
//    }

    // 369
    private int carry = 1;

    public ListNode plusOne(ListNode head) {
        if (plusOneHelper(head).val != 0) return head;
        final ListNode newHead = new ListNode(1);
        newHead.next = head;
        return newHead;
    }

    private ListNode plusOneHelper(ListNode head) {
        if (head == null) return null;
        head.next = plusOneHelper(head.next);
        if (carry == 1) {
            if (head.val != 9) {
                head.val++;
                carry = 0;
                return head;
            }
            head.val = 0;
        }
        return head;
    }

    public ListNode plusOne2(ListNode head) {
        // add a new node for the case 999 + 1
        final ListNode dummy = new ListNode(0);
        dummy.next = head;
        // record the least significant digit that is not 9
        ListNode lastNotNine = dummy, node = head;

        while (node != null) {
            if (node.val != 9) {
                lastNotNine = node;
            }
            node = node.next;
        }
        lastNotNine.val++;
        node = lastNotNine.next;
        while (node != null) {
            node.val = 0;
            node = node.next;
        }
        return dummy.val == 1 ? dummy : dummy.next;
    }



    // 370
    public int[] getModifiedArray(int length, int[][] updates) {
        // add val at the start index and -val at the end index
        int res[] = new int[length];
        for (int[] update : updates) {
            res[update[0]] += update[2];
            int end = update[1];
            if(end < length - 1) {
                res[end + 1] -= update[2];
            }
        }
        for (int i = 1; i < length; i++) {
            res[i] += res[i-1];
        }
        return res;
    }

    // 382
    /** Reservoir Sampling: choose k elements from an array with unknown length */
    public class Solution {
        ListNode head;
        Random randomGen = null;

        /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
        public Solution(ListNode head) {
            this.head = head;
            this.randomGen = new Random();
        }

        /** Returns a random node's value. */
        public int getRandom() {
            ListNode res = null, curr = head;
            for (int i = 1; curr != null; i++) {
                if (randomGen.nextInt(i) == 0) { // returns a number between 0 and i - 1
                    res = curr;
                }
                curr = curr.next;
            }
            return res.val;
        }
    }

    // 388 Longest Absolute File Path

    // 439
    public static String parseTernary(String expression) {
        if (expression == null || expression.length() == 0) return "";
        Deque<Character> stack = new LinkedList<>();

        for (int i = expression.length() - 1; i >= 0; i--) {
            char c = expression.charAt(i);
            if (!stack.isEmpty() && stack.peek() == '?') {

                stack.pop(); //pop '?'
                char first = stack.pop();
                stack.pop(); //pop ':'
                char second = stack.pop();

                if (c == 'T') stack.push(first);
                else stack.push(second);
            } else {
                stack.push(c);
            }
        }

        return String.valueOf(stack.peek());
    }

    // 447
    public int totalHammingDistance(int[] nums) {
        int total = 0, n = nums.length;
        for (int j=0;j<32;j++) {
            int bitCount = 0;
            for (int i=0;i<n;i++)
                bitCount += (nums[i] >> j) & 1;
            total += bitCount*(n - bitCount);
        }
        return total;
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
            if (i < n) s.push(i);
        }
        return res;
    }

    // 592
    public String fractionAddition(String expression) {
        Scanner sc = new Scanner(expression).useDelimiter("/|(?=[-+])");
        int A = 0, B = 1;
        while (sc.hasNext()) {
            int a = sc.nextInt(), b = sc.nextInt();
            A = A * b + a * B;
            B *= b;
            int g = gcd(A, B);
            A /= g;
            B /= g;
        }
        return A + "/" + B;
    }

    private int gcd(int a, int b) {
        return a != 0 ? gcd(b % a, a) : Math.abs(b);
    }
}

package main.java;

import java.util.*;

public class MediumQuestion {
    public static ListNode reverseSecondHalfList(ListNode head) {
        if (head == null) return null;
        ListNode fast = head, slow = head, prev = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }
        prev.next = reverse(slow);
        return head;
    }
    private static ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
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


    // 6

    // 11. Container With Most Water

    // 12. Integer to Roman


    // 148. Sort List



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
        // use | here for explicit comparison
        if (!countUnivalSubtreesHelper(root.left, root.val) | !countUnivalSubtreesHelper(root.right, root.val)) return false;
        res++;
        return root.val == val;
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

    // 357 e.g. 2 -> 91 numbers out of 100 that have unique digits
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) return 1;
        int res = 10;
        int uniqueDigits = 9;
        int availableNumber = 9;
        while (n-- > 1 && availableNumber > 0) {
            uniqueDigits = uniqueDigits * availableNumber;
            res += uniqueDigits;
            availableNumber--;
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
    /** Instead of multiplying by depth, add integers multiple times (by going level by level and
     * adding the unweighted sum to the weighted sum after each level)
     * Input [[1, 1], 2, [1, 1]] -> output 8 (4 * 1 + 2 * 2)
     * */
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

    // 445
    /** numbers are in right order */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<Integer>();
        Stack<Integer> s2 = new Stack<Integer>();

        while(l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        };
        while(l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }
        ListNode res = new ListNode(0);
        int sum = 0;
        while (!s1.isEmpty() || !s2.isEmpty()) {
            sum /= 10;
            if (!s1.isEmpty()) {
                sum += s1.pop();
            }
            if (!s2.isEmpty()) {
                sum += s2.pop();
            }
            ListNode temp = new ListNode(sum / 10);
            res.val = sum % 10;
            temp.next = res;
            res = temp;
        }
        return res.val == 0 ? res.next : res;
    }

    // 582 Kill process
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, List<Integer>> map = new HashMap<>();  // store parent children relationship
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

    public int kthGrammar(int N, int K) {
        if (N == 1) return 0;
        if (K % 2 == 0) return (kthGrammar(N - 1, K / 2) == 0) ? 1 : 0;
        return (kthGrammar(N - 1, (K + 1) / 2) == 0) ? 0 : 1;
    }

    // 731
    class MyCalendarTwo {
        private List<int[]> books = new ArrayList<>();
        public boolean book(int s, int e) {
            MyCalendar overlaps = new MyCalendar();
            for (int[] b : books)
                if (Math.max(b[0], s) < Math.min(b[1], e)) // overlap exist
                    if (!overlaps.book(Math.max(b[0], s), Math.min(b[1], e))) return false; // overlaps overlapped
            books.add(new int[]{ s, e });
            return true;
        }

        private class MyCalendar {
            List<int[]> books = new ArrayList<>();
            public boolean book(int start, int end) {
                for (int[] b : books)
                    if (Math.max(b[0], start) < Math.min(b[1], end)) return false;
                books.add(new int[]{ start, end });
                return true;
            }
        }
    }
}

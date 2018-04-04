package main.java;

import java.util.*;

public class HardQuestion {
    // 4 Median of Two Sorted List
    public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length, n = B.length, l = (m + n + 1) / 2, r = (m + n + 2) / 2;
        return (getkth(A, 0, B, 0, l) + getkth(A, 0, B, 0, r)) / 2.0;
    }

    private double getkth(int[] A, int aStart, int[] B, int bStart, int k) {
        if (aStart > A.length - 1) return B[bStart + k - 1];
        if (bStart > B.length - 1) return A[aStart + k - 1];
        if (k == 1) return Math.min(A[aStart], B[bStart]);

        int aMid = Integer.MAX_VALUE, bMid = Integer.MAX_VALUE;
        if (aStart + k/2 - 1 < A.length) aMid = A[aStart + k/2 - 1];
        if (bStart + k/2 - 1 < B.length) bMid = B[bStart + k/2 - 1];

        if (aMid < bMid)
            return getkth(A, aStart + k/2, B, bStart, k - k/2);// Check: aRight + bLeft
        return getkth(A, aStart, B, bStart + k/2, k - k/2);// Check: bRight + aLeft
    }

    // 146
    public class LRUCache {
        private final int capacity;
        private LinkedHashMap<Integer, Integer> map;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true){
                protected boolean removeEldestEntry(Map.Entry eldest) {
                    return size() > capacity;
                }
            };
        }

        public int get(int key) {
            return map.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            map.put(key, value);
        }
    }

    // 308. Range Sum Query 2D - Mutable
    class NumMatrix {
        private int[][] colSums;
        private int[][] matrix;

        public NumMatrix(int[][] matrix) {
            int row = matrix.length;
            if (row == 0 || matrix[0].length == 0) return;
            int col = matrix[0].length;
            this.matrix = matrix;
            this.colSums = new int[row + 1][col];

            for (int i = 1; i <= row; i++)
                for (int j = 0; j < col; j++)
                    colSums[i][j] = colSums[i - 1][j] + matrix[i-1][j];
        }

        public void update(int row, int col, int val) {
            for(int i = row + 1; i < colSums.length; i++)
                colSums[i][col] = colSums[i][col] - matrix[row][col] + val;
            matrix[row][col] = val;
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int res = 0;
            for (int j = col1; j <= col2; j++)
                    res += colSums[row2 + 1][j] - colSums[row1][j];
            return res;
        }
    }
}

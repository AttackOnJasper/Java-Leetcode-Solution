package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortingProblems {
    // Quick Sort
    public int[] quickSort(int[] arr) {
        return quickSortHelper(arr, 0, arr.length - 1);
    }

    private int[] quickSortHelper(int[] arr, int low, int high) {
        int i = low, j = high;
        int pivot = arr[low + (high-low)/2];
        while (i <= j) {
            while (arr[i] < pivot) {
                i++;
            }
            while (arr[j] > pivot) {
                j--;
            }
            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        if (low < j)
            return quickSortHelper(arr, low, j);
        if (i < high)
            return quickSortHelper(arr, i, high);
        return arr;
    }

    // quick select
    public int selectK(int[] arr, int k) {
        if (arr.length < 10) {
            return selectKShort(arr, k);
        }
        int pivot = findPivot(arr);
        List<Integer> left = new ArrayList<>(), right = new ArrayList<>();
        for (int n : arr) {
            if (n > pivot) {
                right.add(n);
            } else {
                left.add(n);
            }
        }
        if (left.size() > k) return selectK(left.stream().mapToInt(i -> i).toArray(), k);
        return selectK(right.stream().mapToInt(i -> i).toArray(), k - left.size());
    }

    private int findPivot(int[] arr) {
        int[] groupsOf5 = new int[arr.length / 5 + 1];
        for (int i = 0, j = 0; i < arr.length; i += 5, j++) {
            groupsOf5[j] = (i + 5 >= arr.length) ?
                selectKShort(Arrays.copyOfRange(arr, i, arr.length - 1), (arr.length - i) / 2) :
                selectKShort(Arrays.copyOfRange(arr, i, i + 5), 2);
        }
        return selectK(groupsOf5, groupsOf5.length / 2);
    }

    private int selectKShort(int[] arr, int k) {
        Arrays.sort(arr);
        return arr[k];
    }



    // Merge Sort
    private int[] merge(int[] first, int[] second) {
        int i = first.length - 1, j = second.length - 1, newIndex = i + j + 1;
        int[] res = new int[newIndex + 1];
        while (i >= 0 && j >= 0) {
            res[newIndex--] = first[i] > second[j] ? first[i--] : second[j--];
        }
        while (j >= 0) {
            res[newIndex--] = second[j--];
        }
        while (i >= 0) {
            res[newIndex--] = first[i--];
        }
        return res;
    }

    public int[] mergeSort(int[] arr) {
        if (arr.length < 2) return arr;
        if (arr.length == 2) {
            if (arr[0] > arr[1]) {
                int temp = arr[0];
                arr[0] = arr[1];
                arr[1] = temp;
            }
            return arr;
        }
        int[] frontArr = Arrays.copyOfRange(arr, 0, arr.length / 2);
        int[] backArr = Arrays.copyOfRange(arr, arr.length / 2, arr.length);
        return merge(mergeSort(frontArr), mergeSort(backArr));
    }
}

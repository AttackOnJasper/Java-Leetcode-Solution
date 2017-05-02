package main;

public class EasyQuestion {
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

    public int findComplement(int num) {
        return (Integer.highestOneBit(num) << 1) - 1 - num;
    }


    public static void main(String[] args) {
        EasyQuestion eq = new EasyQuestion();
        System.out.println(eq.reverseString("olleh"));
        System.out.println(eq.findComplement(5));
    }
}

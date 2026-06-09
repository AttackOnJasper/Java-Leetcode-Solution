package com.jasperwang.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Math-focused LeetCode solutions and related utilities.
 *
 * <p>The methods are grouped by problem domain rather than by difficulty. Most implementations are
 * self-contained so they can be copied into individual LeetCode submissions.
 */
public class MathQuestion {

    /**
     * LeetCode 69.
     * <p>
     * newton's method.
     *
     * @param x input value
     * @return result
     */
    public int mySqrt(int x) {
        long r = x;
        while (r * r > x) {
            r = (r + x / r) / 2;
        }
        return (int) r;
    }

    /**
     * LeetCode 191: hamming weight (bitCount) bit manipulation.
     *
     * @param n input value
     * @return result
     */
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }

    /**
     * bit shift.
     *
     * @param n input value
     * @return result
     */
    public int hammingWeight2(int n) {
        int count = 0;
        while (n != 0) {
            count += n & 1;
            n = n >>> 2;
        }
        return count;
    }

    /**
     * LeetCode 202: is happy number.
     * <p>
     * test cycle.
     *
     * @param n input value
     * @return result
     */
    public boolean isHappy(int n) {
        int i1 = n, i2 = next(n);
        while (i2 != i1) {
            i1 = next(i1);
            i2 = next(next(i2));
        }
        return i1 == 1;
    }

    private int next(int n) {
        int res = 0;
        while (n != 0) {
            int t = n % 10;
            res += t * t;
            n /= 10;
        }
        return res;
    }

    /**
     * LeetCode 204: count Primes.
     * <p>
     * semi-dp idea.
     *
     * @param n input value
     * @return result
     */
    public int countPrimes(int n) {
        int res = 0;
        boolean[] notPrime = new boolean[n];
        for (int i = 2; i < n; i++) {
            if (!notPrime[i]) {
                res++;
                for (int j = 2; i * j < n; j++) {
                    notPrime[i * j] = true;
                }
            }
        }
        return res;
    }

    /**
     * LeetCode 326: power of Three.
     *
     * @param n input value
     * @return result
     */
    public boolean isPowerOfThree(int n) {
        // 1162261467 is 3^19,  3^20 is bigger than int
        return (n > 0 && 1162261467 % n == 0);
    }

    /**
     * LeetCode 231: is power of two.
     *
     * @param n input value
     * @return result
     */
    public boolean isPowerOfTwo(int n) {
        return n > 0 && ((n & (n - 1)) == 0);
    }

    /**
     * LeetCode 342: is power of four.
     *
     * @param num input value
     * @return result
     */
    public boolean isPowerOfFour(int num) {
        return num > 0 && (num & (num - 1)) == 0 && (num - 1) % 3 == 0;
    }

    /**
     * LeetCode 367: perfect square sequence 1 + 3 + 5 + 7.
     *
     * @param num input value
     * @return result
     */
    public boolean isPerfectSquare(int num) {
        if (num < 1) {
            return false;
        }
        for (int i = 1; num > 0; i += 2) {
            num -= i;
        }
        return num == 0;
    }

    /**
     * binary search to find the square root.
     *
     * @param num input value
     * @return result
     */
    public boolean isPerfectSquare1(int num) {
        if (num < 1) {
            return false;
        }
        long left = 1, right = num; // long type to avoid 2147483647 case

        while (left <= right) {
            long mid = left + (right - left) / 2;
            long t = mid * mid;
            if (t > num) {
                right = mid - 1;
            } else if (t < num) {
                left = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * newton's method to find the square root
     */
    boolean isPerfectSquare2(int num) {
        if (num < 1) {
            return false;
        }
        if (num == 1) {
            return true;
        }
        long t = num / 2;
        while (t * t > num) {
            t = (t + num / t) / 2;
        }
        return t * t == num;
    }

    /**
     * LeetCode 371: get Sum Implement sum without +.
     *
     * @param a input value
     * @param b input value
     * @return result
     */
    public int getSum(int a, int b) {
        return b == 0 ? a : getSum(a ^ b, (a & b) << 1);
    }

    /**
     * LeetCode 405: converts a number to hexadecimal.
     */
    private char[] map = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };

    /**
     * LeetCode 405: converts a number to hexadecimal.
     *
     * @param num input value
     * @return result
     */
    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        String result = "";
        while (num != 0) {
            result = map[(num & 15)] + result;
            num = (num >>> 4); // >> is arithmetic shift right, >>> is logical shift right
        }
        return result;
    }

    /**
     * LeetCode 447: find the number of 2 points that have same distance towards one point store the distance to point i
     * & number of coordinates for that distance.
     *
     * @param points input value
     * @return result
     */
    public int numberOfBoomerangs(int[][] points) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }
                int d = getDistance(points[i], points[j]);
                map.put(d, map.getOrDefault(d, 0) + 1);
            }
            for (int val : map.values()) {
                res += val * (val - 1);
            }
            map.clear();
        }
        return res;
    }

    private int getDistance(int[] a, int[] b) {
        int dx = a[0] - b[0];
        int dy = a[1] - b[1];

        return dx * dx + dy * dy;
    }

    /**
     * LeetCode 461: hamming distance.
     *
     * @param x input value
     * @param y input value
     * @return result
     */
    public int hammingDistance(int x, int y) {
        /** int bitCount(int n) { while(n) { n = n & (n-1); count++; } return count; } */
        return Integer.bitCount(x ^ y);
    }

    /**
     * LeetCode 476: number Complement.
     *
     * @param num input value
     * @return result
     */
    public int findComplement(int num) {
        return (largest_power(num) << 1) - 1 - num;
    }

    /**
     * Equal to highestOneBit
     */
    private int largest_power(int N) {
        // changing all right side bits to 1.
        N = N | (N >> 1);
        N = N | (N >> 2);
        N = N | (N >> 4);
        N = N | (N >> 8);
        N = N | (N >> 16);
        return (N + 1) >> 1;
    }

    /**
     * find complement2.
     *
     * @param num input value
     * @return result
     */
    public int findComplement2(int num) {
        // highestOneBit(n) returns 2^m, where 2^m < n < 2^(m+1)
        return ~num & ((Integer.highestOneBit(num) << 1) - 1);
    }

    /**
     * LeetCode 479: largest Palindrome Product: return the max palindrome from product of 2 n-digit numbers.
     *
     * @param n input value
     * @return result
     */
    public int largestPalindrome(int n) {
        if (n == 1) {
            return 9;
        }
        // if n = 3 then upperBound = 999 and lowerBound = 99
        int upperBound = (int) Math.pow(10, n) - 1, lowerBound = upperBound / 10;
        long maxNumber = (long) upperBound * (long) upperBound;

        // represents the first half of the maximum assumed palindrom.
        // e.g. if n = 3 then maxNumber = 999 x 999 = 998001 so firstHalf = 998
        int firstHalf = (int) (maxNumber / (long) Math.pow(10, n));

        boolean palindromFound = false;
        long palindrom = 0;

        while (!palindromFound) {
            // creates maximum assumed palindrom
            // e.g. if n = 3 first time the maximum assumed palindrom will be 998 899
            palindrom = createPalindrom(firstHalf);

            // here i and palindrom/i forms the two factor of assumed palindrom
            for (long i = upperBound; upperBound > lowerBound; i--) {
                // if n= 3 none of the factor of palindrom  can be more than 999 or less than square root of
                // assumed palindrom
                if (palindrom / i > maxNumber || i * i < palindrom) {
                    break;
                }

                // if two factors found, where both of them are n-digits,
                if (palindrom % i == 0) {
                    palindromFound = true;
                    break;
                }
            }
            firstHalf--;
        }
        return (int) (palindrom % 1337);
    }

    private long createPalindrom(long num) {
        String str = num + new StringBuilder().append(num).reverse().toString();
        return Long.parseLong(str);
    }
}

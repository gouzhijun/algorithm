package com.gouzhijun.algorithm.dp;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: gouzhijun
 * @Date: 2020/7/7
 */
public class LengthofLongestFibonacciSubsequence {
    public int lenLongestFibSubseq(int[] A) {
        Set<Integer> set = new HashSet<Integer>();
        for (Integer x : A) {
            set.add(x);
        }
        int max = 0;
        for (int i = 0; i < A.length - 1; i++) {
            for (int j = i + 1; j < A.length; j++) {
                int x1 = A[i];
                int x2 = A[j];
                int sum = x1 + x2;
                int len = 2;
                while (set.contains(sum)) {
                    len++;
                    x1 = x2;
                    x2 = sum;
                    sum = x1 + x2;
                }
                if (len > 2 && len > max) {
                    max = len;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LengthofLongestFibonacciSubsequence sequence = new LengthofLongestFibonacciSubsequence();
        int len = sequence.lenLongestFibSubseq(new int[]{1,3,7,11,12,14,18});
        System.out.println("len : " + len);
    }
}

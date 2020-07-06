package com.gouzhijun.algorithm.dp;

import java.util.Arrays;

/**
 * @Author: gouzhijun
 * @Date: 2020/7/3
 */
public class LongestIncreasingSubsequence {

    /**
     * Given an unsorted array of integers, find the length of longest increasing subsequence.
     * Example:
     *
     * Input: [10,9,2,5,3,7,101,18]
     * Output: 4
     * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] maxLens = new int[nums.length];
        Arrays.fill(maxLens, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] <= nums[j]) {
                    continue;
                }
                maxLens[i] = Math.max(maxLens[j] + 1, maxLens[i]);
            }
        }
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (maxLens[i] > max) {
                max = maxLens[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence instance = new LongestIncreasingSubsequence();
        System.out.println(instance.lengthOfLIS(new int[]{1,3,6,7,9,4,10,5,6}));
    }

}

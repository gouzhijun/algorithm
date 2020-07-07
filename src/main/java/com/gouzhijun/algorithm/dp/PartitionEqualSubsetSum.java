package com.gouzhijun.algorithm.dp;

import java.util.Arrays;

/**
 * Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 *
 * Note:
 *
 * Each of the array element will not exceed 100.
 * The array size will not exceed 200.
 *
 * https://leetcode.com/problems/partition-equal-subset-sum/
 *
 * Input: [1, 5, 11, 5]
 *
 * Output: true
 *
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 *
 * @Author: gouzhijun
 * @Date: 2020/7/7
 */
public class PartitionEqualSubsetSum {

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        Arrays.sort(nums);
        return canPartition0(nums, nums.length - 1, sum / 2);
    }

    private boolean canPartition0(int[] nums, int k, int sum) {
        if (sum == 0) {
            return true;
        }
        if (k < 0 || nums[k] > sum) {
            return false;
        }
        return canPartition0(nums, k - 1, sum - nums[k]) || canPartition0(nums, k - 1, sum);
    }

    public static void main(String[] args) {
        PartitionEqualSubsetSum subsetSum = new PartitionEqualSubsetSum();
        System.out.println("result : " + subsetSum.canPartition(new int[]{1, 5, 11, 5}));
    }

}

package com.gouzhijun.algorithm.dp;

/**
 * Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * Output: True
 * Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 *
 *
 *https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
 *
 */
public class PartitiontoKEqualSumSubsets {

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false;
        }
        int target = sum/k;
        boolean[] flags = new boolean[nums.length];
        return traversal(nums, k * nums.length - 1, target, target, k, flags);
    }

    private boolean traversal(int[] nums, int index, int sum, int target, int times, boolean[] flags) {
        if (sum == 0) {
            if (times == 1) {
                return true;
            }
            times--;
            sum = target;
            index = (index / nums.length) * nums.length -1;
        }
        if (index < (times - 1) * nums.length) {
            return false;
        }

        boolean result = traversal(nums, index - 1, sum, target, times, flags);
        int i = index % nums.length;
        if (!result && !flags[i] && nums[i] <= sum) {
            flags[i] = true;
            result = traversal(nums, index - 1, sum - nums[i], target, times, flags);
            flags[i] = false;
        }
        return result;
    }

    public static void main(String[] args) {
        PartitiontoKEqualSumSubsets partitiontoKEqualSumSubsets = new PartitiontoKEqualSumSubsets();
        boolean result = partitiontoKEqualSumSubsets.canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 4);
        System.out.println("result :" + result);
    }


}

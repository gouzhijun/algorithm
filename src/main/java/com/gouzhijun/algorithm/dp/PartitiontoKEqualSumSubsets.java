package com.gouzhijun.algorithm.dp;

import java.util.Arrays;

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

    // TODO: dynamic program & bit masking
    // TODO: program by myself
    // https://leetcode.com/problems/partition-to-k-equal-sum-subsets/discuss/335668/DP-with-Bit-Masking-Solution-%3A-Best-for-Interviews
    public boolean canPartitionKSubsetsWithBitMasking(int[] nums, int k) {
        int N = nums.length;
        Arrays.sort(nums);
        int sum = Arrays.stream(nums).sum();
        int target = sum / k;
        if (sum % k > 0 || nums[N - 1] > target) return false;

        boolean[] dp = new boolean[1 << N];
        dp[0] = true;
        int[] total = new int[1 << N];



        for (int state = 0; state < (1 << N); state++) {
            if (!dp[state]) continue;
            for (int i = 0; i < N; i++) {
                int future = state | (1 << i);
                if (state != future && !dp[future]) {
                    if (nums[i] <= target - (total[state] % target)) {
                        dp[future] = true;
                        total[future] = total[state] + nums[i];
                    } else {
                        break;
                    }
                }
            }
        }
        return dp[(1 << N) - 1];
    }

    public static void main(String[] args) {
        PartitiontoKEqualSumSubsets partitiontoKEqualSumSubsets = new PartitiontoKEqualSumSubsets();
        boolean result = partitiontoKEqualSumSubsets.canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 4);
        System.out.println("result :" + result);
    }


}

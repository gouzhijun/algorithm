package com.gouzhijun.algorithm.dp;

/**
 * @Author:gouzhijun
 * @Date:2020/7/2
 */
public class MaximumSubarray {


    /**
     * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
     * thought: f(i) = max( f(n-1) + a[n] : a[n])
     * @param nums
     * @return
     */

    private int max;

    public int maxSubArray(int[] nums) {
        max = nums[0];
        maxArray(nums, nums.length - 1);
        return max;
    }


    private int maxArray(int[] nums, int i) {
        if (i == 0) {
            return nums[0];
        }
        int subValue = maxArray(nums, i -1);
        int value = subValue > 0 ? subValue + nums[i] : nums[i];
        if (value > max) {
            max = value;
        }
        return value;
    }


    public static void main(String[] args) {
        MaximumSubarray maximumSubarray = new MaximumSubarray();
        int maxValue = maximumSubarray.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4});
        System.out.println("max: " + maxValue);
    }

}

package com.gouzhijun.algorithm.dp;

/**
 * @Author: gouzhijun
 * @Date: 2020/7/2
 */
public class MaximumProductSubarray {

    /**
     * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
     * p(k) = max(a(k) >= 0 ? a(k) * p(k - 1), a(k))
     * n(k) = min(a(k) <= 0 ? a(k) * n(k - 1), a(k))
     * p(k) positive max end with k-th
     * n(k) negative max end with k-th
     * use max, min to include 0 problem
     * @param nums
     * @return
     */

    public int maxProduct(int[] nums) {
        Integer[] positiveMaxArray = new Integer[nums.length + 1];
        Integer[] negativeMaxArray = new Integer[nums.length + 1];
        int first = nums[0];
        if (first >= 0) {
            positiveMaxArray[0] = first;
            negativeMaxArray[0] = 0;
        } else {
            positiveMaxArray[0] = 0;
            negativeMaxArray[0] = first;
        }

        for (int i = 0; i < nums.length; i++) {
            positiveMax(nums, i, positiveMaxArray, negativeMaxArray);
        }
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (positiveMaxArray[i] > max) {
                max = positiveMaxArray[i];
            }
        }
        return max;
    }

    private int positiveMax(int[] nums, int k, Integer[] positiveMaxArray, Integer[] negativeMaxArray) {
        if (positiveMaxArray[k] != null) {
            return positiveMaxArray[k];
        }
        Integer maxValue = null;
        if (nums[k] >= 0) {
            maxValue =  Math.max(nums[k], nums[k] * positiveMax(nums, k -1, positiveMaxArray, negativeMaxArray));
        } else {
            maxValue =  nums[k] * negativeMax(nums, k - 1, positiveMaxArray, negativeMaxArray);
        }
        positiveMaxArray[k] = maxValue;
        return maxValue;

    }

    private int negativeMax(int[] nums, int k, Integer[] positiveMaxArray, Integer[] negativeMaxArray) {
        if (negativeMaxArray[k] != null) {
            return negativeMaxArray[k];
        }
        Integer minValue = null;
        if (nums[k] <= 0) {
            minValue =  Math.min(nums[k], nums[k] * positiveMax(nums, k - 1, positiveMaxArray, negativeMaxArray));
        } else {
            minValue =  nums[k] * negativeMax(nums, k - 1, positiveMaxArray, negativeMaxArray);
        }
        negativeMaxArray[k] = minValue;
        return minValue;
    }

    public static void main(String[] args) {
        MaximumProductSubarray maximumProductSubarray = new MaximumProductSubarray();
        int value = maximumProductSubarray.maxProduct(new int[]{2, 3, -2, 4});
        System.out.println("max value : " + value);
    }


}

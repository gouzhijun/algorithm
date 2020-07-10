package com.gouzhijun.algorithm.dp;

/**
 * Given an array, strs, with strings consisting of only 0s and 1s. Also two integers m and n.
 *
 * Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.
 *
 * Example 1:
 *
 * Input: strs = ["10","0001","111001","1","0"], m = 5, n = 3
 * Output: 4
 * Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are "10","0001","1","0".
 *
 * Example 2:
 *
 * Input: strs = ["10","0","1"], m = 1, n = 1
 * Output: 2
 * Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".
 *
 * state transfer:
 *
 * F[n][m][k] = max(F[n-1][m][k], F[n-1][m - strs[n-1](count of 1)][k - strs[count of 0)]]
 */
public class OnesAndZeros {

    public int findMaxForm(String[] strs, int m, int n) {
        int[][][] matrix = new int[strs.length + 1][m + 1][n + 1];
        init(matrix);
        matrix[0][0][0] = 0;
        for (int index = 0; index < strs.length; index++) {
            String str = strs[index];
            int[] cnt = countOnesAndZeros(str);
            for (int i = 0; i < m + 1; i++) {
                for (int j = 0; j < n + 1; j++) {
                    if (matrix[index][i][j] == -1) continue;
                    int tmp = matrix[index][i][j];
                    matrix[index + 1][i][j] = Math.max(matrix[index + 1][i][j], tmp);
                    if (i + cnt[0] <= m && j + cnt[1] <= n) {
                        matrix[index + 1][i + cnt[0]][j + cnt[1]] = Math.max(matrix[index + 1][i + cnt[0]][j + cnt[1]], tmp + 1);
                    }
                }
            }
        }
        return findMax(matrix);
    }


    public int[] countOnesAndZeros(String str) {
        int[] count = new int[2];
        for (char c : str.toCharArray()) {
            if (c == '0') {
                count[0] += 1;
            } else {
                count[1] += 1;
            }
        }
        return count;
    }

    public void init(int[][][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                for (int k = 0; k < matrix[0][0].length; k++) {
                    matrix[i][j][k] = -1;
                }
            }
        }
    }

    public int findMax(int[][][] matrix) {
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                for (int k = 0; k < matrix[0][0].length; k++) {
                    if (matrix[i][j][k] > max) {
                        max = matrix[i][j][k];
                    }
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        OnesAndZeros solution = new OnesAndZeros();
        int count = solution.findMaxForm0(new String[]{"10","0001","111001","1","0"}, 5, 3);
        System.out.println("result : " + count);
    }

    public int findMaxForm0(String[] strs, int m, int n) {
        int[][] dp = new int[m+1][n+1];
        for (String s : strs) {
            int[] count = count(s);
            for (int i=m;i>=count[0];i--)
                for (int j=n;j>=count[1];j--)
                    dp[i][j] = Math.max(1 + dp[i-count[0]][j-count[1]], dp[i][j]);
        }
        return dp[m][n];
    }

    public int[] count(String str) {
        int[] res = new int[2];
        for (int i=0;i<str.length();i++)
            res[str.charAt(i) - '0']++;
        return res;
    }
}

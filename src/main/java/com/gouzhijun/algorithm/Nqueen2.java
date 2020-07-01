package com.gouzhijun.algorithm;

/**
 * @Author: gouzhijun
 * @Date: 2020/7/1
 */
public class Nqueen2 {

    public int totalNQueens(int n) {
        boolean[] col = new boolean[n];
        boolean[] diag1 = new boolean[2 * n - 1];
        boolean[] diag2 = new boolean[2 * n - 1];
        return countNQueue(0, col, diag1, diag2);
    }

    private int countNQueue(int rowCnt, boolean[] col, boolean[] diag1, boolean[] diag2) {
        if (rowCnt == col.length) {
            return 1;
        }
        int cnt = 0;
        for (int colIndex = 0; colIndex < col.length; colIndex++) {
            if (col[colIndex] || diag1[col.length - colIndex + rowCnt - 1] || diag2[colIndex + rowCnt]) {
                continue;
            }
            col[colIndex] = true;
            diag1[col.length - colIndex + rowCnt - 1] = true;
            diag2[colIndex + rowCnt] = true;
            cnt += countNQueue(rowCnt + 1, col, diag1, diag2);
            col[colIndex] = false;
            diag1[col.length - colIndex + rowCnt - 1] = false;
            diag2[colIndex + rowCnt] = false;
        }
        return cnt;
    }

    public static void main(String[] args) {
        Nqueen2 nquee2 = new Nqueen2();
        System.out.println("result : " + nquee2.totalNQueens(8));
    }


}

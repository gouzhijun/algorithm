package com.gouzhijun.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @Author: gouzhijun
 * @Date: 2020/7/1
 */
public class Nqueen {
    public List<List<String>> solveNQueens(int n) {
        Set<String> availablePositions = initAvailablePositions(n);
        List<List<String>> solves = new ArrayList();
        traversal(availablePositions, n, solves, buildInitPositionsMap(n));
        return solves;
    }

    public void traversal(Set<String> availablePositions, int remain, List<List<String>> solves,
                          List<String> currPosition) {
        if (remain == 0) {
            solves.add(currPosition);
            return;
        }
        if (availablePositions.size() == 0) {
            return;
        }
        Iterator<String> iter = availablePositions.iterator();
        while(iter.hasNext()) {
            String availPosition = iter.next();
            Set<String> newAvailablePoistions =  updateAvailablePositions(availPosition, availablePositions);
            String[] coord =  availPosition.split("_");
            String originStr = currPosition.get(Integer.valueOf(coord[0]));
            char[] charArray = originStr.toCharArray();
            charArray[Integer.valueOf(coord[1])] = 'Q';
            currPosition.set(Integer.valueOf(coord[0]), new String(charArray));
            traversal(newAvailablePoistions, remain - 1, solves, new ArrayList(currPosition));
            currPosition.set(Integer.valueOf(coord[0]), originStr);
            iter.remove();
        }

    }


    private Set<String> initAvailablePositions(int n) {
        Set<String> availablePositions = new HashSet();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                availablePositions.add(buildPosition(i, j));
            }
        }
        return availablePositions;
    }

    private List<String> buildInitPositionsMap(int n) {
        List<String> positionMap = new ArrayList();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            builder.append('.');
        }
        String row = builder.toString();
        for (int i = 0; i < n; i++) {
            positionMap.add(row);
        }
        return positionMap;
    }

    private String buildPosition(int i, int j) {
        return i + "_" + j;
    }

    private Set<String> updateAvailablePositions(String availPosition, Set<String> availPositions) {
        Set<String> tmpPositions = new HashSet(availPositions);
        Iterator<String> iter = tmpPositions.iterator();
        while(iter.hasNext()) {
            String position = iter.next();
            if (reach(availPosition, position)) {
                iter.remove();
            }
        }
        return tmpPositions;
    }

    private boolean reach(String position1, String position2) {
        String[] positionArray1 = position1.split("_");
        String[] positionArray2 = position2.split("_");
        if (positionArray1[0].equals(positionArray2[0]) || positionArray1[1].equals(positionArray2[1])) {
            return true;
        }
        if (Math.abs(Integer.valueOf(positionArray1[0]) - Integer.valueOf(positionArray2[0])) ==
                Math.abs(Integer.valueOf(positionArray1[1]) - Integer.valueOf(positionArray2[1]))) {
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        Nqueen nquee = new Nqueen();
        List<List<String>> solves = nquee.solveNQueens(3);
        int i = 0;
        for (List<String> solve : solves) {
            i++;
            System.out.println("result" + i + ":");
            for (String row : solve) {
                System.out.println(row);
            }
        }
    }
}

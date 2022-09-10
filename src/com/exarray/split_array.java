package com.exarray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.IntBinaryOperator;

public class split_array {
    public static List<Integer> enterNos() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Find multiple ways to split an array into 2 non-empty continuous sub-arrays and compare the largest sum between sub-arrays.");
        System.out.println("Compare those largest sums to find the smallest sum.");
        System.out.println("Enter integers for the array (press Enter to separate numbers, '0' to close the array) : ");
        List<Integer> noArray = new ArrayList<>();
        while (true) {
            int input = scanner.nextInt();
            if (input == 0) {
                break;
            }
            noArray.add(input);
        }
        return noArray;
    }

    public static Integer compSum(Integer l, Integer r, boolean largest) {
        IntBinaryOperator max;
        if (largest) {
            max = (x, y) -> x > y ? x : y;
        } else {
            max = (x, y) -> x < y ? x : y;
        }
        return max.applyAsInt(l, r);
    }

    public static List<Integer> storeArray(List<Integer> intArray, Integer length, Integer start) {
        List<Integer> ary = new ArrayList<>();
        Integer x = start;
        for (int i = 0; i < length; i++) {
            ary.add(intArray.get(x));
            ++x;
        }
        return ary;
    }

    public static void main(String[] args) throws Exception {
        List<Integer> intArray = enterNos();
        List<Integer> ary1 = new ArrayList<>();
        List<Integer> ary2 = new ArrayList<>();
        Integer noCount = intArray.size();
        Integer compSum = 0;
        Integer smallSum = null;
        Integer spCount = 0;
        System.out.println("You entered: " + intArray);
        System.out.println(noCount);

        if (noCount == 1) {
            System.out.println("You only have 1 element in the array . ");
            smallSum = intArray.get(0);
        } else if (noCount == 2) {
            ary1.add(intArray.get(0));
            ary2.add(intArray.get(1));
            System.out.println("split :: array 1 : " + ary1 + " and array 2 : " + ary2);
            smallSum = compSum(intArray.get(0), intArray.get(1), false);
        } else {
            for (int i = 1; i < noCount; i++) {
                spCount = noCount - i;
                ary1 = storeArray(intArray, i, 0);
                ary2 = storeArray(intArray, spCount, i);
                System.out.println("split :: array 1 : " + ary1 + " and array 2 : " + ary2);
                compSum = compSum(ary1.stream().mapToInt(Integer::intValue).sum(), ary2.stream().mapToInt(Integer::intValue).sum(), true);
                System.out.println("largest sum : " + compSum);
                if (smallSum == null) {
                    smallSum = compSum;
                } else {
                    smallSum = compSum(smallSum, compSum, false);
                }
            }
        }
        System.out.println("Comparing sums on all split, the smallest sum is " + smallSum);
    }
}

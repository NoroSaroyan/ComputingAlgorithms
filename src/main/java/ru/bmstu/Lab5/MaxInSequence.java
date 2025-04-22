package ru.bmstu.Lab5;

import java.util.Scanner;

public class MaxInSequence {
    public static void main(String[] args) {
        System.out.println("Введите последовательность натуральных чисел, заканчивающуюся 0:");
        System.out.println("Максимум: " + findMax());
    }

    public static long findMax() {
        Scanner sc = new Scanner(System.in);
        long num = sc.nextLong();
        if (num == 0) return 0;
        long nextMax = findMax();
        return Math.max(num, nextMax);
    }
}

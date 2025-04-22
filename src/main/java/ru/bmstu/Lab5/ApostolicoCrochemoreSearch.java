package ru.bmstu.Lab5;

public class ApostolicoCrochemoreSearch {
    public static void main(String[] args) {
        String text = "ababcabcababc";
        String pattern = "abcab";
        System.out.println(text + " " + pattern);
        apostolicoCrochemore(text, pattern);
    }

    public static void apostolicoCrochemore(String text, String pattern) {
        int m = pattern.length();
        int n = text.length();
        int[] kmpNext = computeKMPNext(pattern);
        int[] failure = computeFailure(pattern);
        int shift = 0;
        int j = 0;
        int i = 0;

        while (shift <= n - m) {
            while (j < m && pattern.charAt(j) == text.charAt(shift + j)) {
                j++;
            }

            if (j == m) {
                System.out.println("Подстрока найдена по индексу " + shift);
            }

            if (j == 0) {
                shift++;
            } else {
                int k = failure[j - 1];
                shift += (j - k);
                j = Math.max(0, k);
            }
        }
    }

    public static int[] computeKMPNext(String pattern) {
        int[] next = new int[pattern.length()];
        int j = 0;

        for (int i = 1; i < pattern.length(); i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = next[j - 1];
            }

            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }

            next[i] = j;
        }
        return next;
    }

    public static int[] computeFailure(String pattern) {
        return computeKMPNext(pattern); // в этом алгоритме используется как KMP
    }
}


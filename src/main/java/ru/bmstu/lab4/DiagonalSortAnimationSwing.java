package ru.bmstu.lab4;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class DiagonalSortAnimationSwing {
    public static void main(String[] args) {
        String[] options = {"Console", "Graphical"};
        int mode = JOptionPane.showOptionDialog(
                null,
                "Выберите режим работы:",
                "Меню выбора режима",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );

        try {
            int[][] matrix = readMatrixFromFile("/Users/noriksaroyan/IntellijProjects/ComputingAlgorithms/src/main/java/ru/bmstu/lab4/input.txt");
            int[] diagonal = extractMainDiagonal(matrix);
            if (mode == 0) {
                System.out.println("Исходная матрица:");
                printMatrix(matrix);
                System.out.println("\nГлавная диагональ до сортировки: " + Arrays.toString(diagonal));
                combSort(diagonal);
                System.out.println("Главная диагональ после сортировки: " + Arrays.toString(diagonal));
            } else if (mode == 1) {
                SwingUtilities.invokeLater(() -> new SortFrame(diagonal));
            } else {
                System.exit(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ошибка чтения файла: " + e.getMessage());
        }
    }

    private static int[][] readMatrixFromFile(String filename) throws IOException {
        try (Scanner sc = new Scanner(new File(filename))) {
            int rows = sc.nextInt();
            int cols = sc.nextInt();
            int[][] mat = new int[rows][cols];
            for (int i = 0; i < rows; i++)
                for (int j = 0; j < cols; j++)
                    mat[i][j] = sc.nextInt();
            return mat;
        }
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix)
            System.out.println(Arrays.toString(row));
    }

    private static int[] extractMainDiagonal(int[][] matrix) {
        int n = Math.min(matrix.length, matrix[0].length);
        int[] diag = new int[n];
        for (int i = 0; i < n; i++) diag[i] = matrix[i][i];
        return diag;
    }

    private static void combSort(int[] array) {
        int gap = array.length;
        boolean swapped = true;
        double shrink = 1.3;
        while (gap > 1 || swapped) {
            gap = (int) (gap / shrink);
            if (gap < 1) gap = 1;
            swapped = false;
            for (int i = 0; i + gap < array.length; i++) {
                if (array[i] > array[i + gap]) {
                    int temp = array[i];
                    array[i] = array[i + gap];
                    array[i + gap] = temp;
                    swapped = true;
                }
            }
        }
    }
}

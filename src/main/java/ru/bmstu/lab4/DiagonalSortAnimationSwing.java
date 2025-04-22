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

class SortFrame extends JFrame {
    public SortFrame(int[] diagonal) {
        setTitle("Анимация сортировки расчёской");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SortPanel panel = new SortPanel(diagonal);
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        panel.startAnimation();
    }
}

class SortPanel extends JPanel {
    private int[] array;
    private int n;
    private int gap;
    private boolean swapped = true;
    private int index = 0;
    private final double shrink = 1.3;
    private Timer timer;

    private final int BOX_SIZE = 60;
    private final int PADDING = 20;
    private int[] xPos;
    // animation fields
    private boolean animating = false;
    private int animA, animB;
    private int targetAX, targetBX;
    private int step = 0, stepsCount = 12;

    public SortPanel(int[] diagonal) {
        this.array = Arrays.copyOf(diagonal, diagonal.length);
        this.n = array.length;
        this.gap = n;
        this.xPos = new int[n];
        for (int i = 0; i < n; i++)
            xPos[i] = PADDING + i * BOX_SIZE;
        int width = n * BOX_SIZE + PADDING * 2;
        int height = BOX_SIZE + PADDING * 2;
        setPreferredSize(new Dimension(width, height));
    }

    public void startAnimation() {
        timer = new Timer(50, e -> {
            if (!animating) stepSort();
            animateSwap();
            repaint();
        });
        timer.start();
    }

    private void stepSort() {
        if (index + gap >= n) {
            if (gap > 1 || swapped) {
                gap = (int)(gap / shrink);
                if (gap < 1) gap = 1;
                swapped = false;
                index = 0;
            } else {
                timer.stop();
            }
            return;
        }
        if (array[index] > array[index + gap]) {
            animating = true;
            animA = index;
            animB = index + gap;
            targetAX = xPos[animB];
            targetBX = xPos[animA];
            step = 0;
        }
        index++;
    }

    private void animateSwap() {
        if (!animating) return;
        int dxA = (targetAX - xPos[animA]) / (stepsCount - step);
        int dxB = (targetBX - xPos[animB]) / (stepsCount - step);
        xPos[animA] += dxA;
        xPos[animB] += dxB;
        step++;
        if (step >= stepsCount) {
            int temp = array[animA]; array[animA] = array[animB]; array[animB] = temp;
            int tempX = xPos[animA]; xPos[animA] = xPos[animB]; xPos[animB] = tempX;
            swapped = true;
            animating = false;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int i = 0; i < n; i++) {
            int x = xPos[i];
            int y = PADDING;
            g2.setColor(animating && (i == animA || i == animB) ? Color.ORANGE : Color.LIGHT_GRAY);
            g2.fillRect(x, y, BOX_SIZE, BOX_SIZE);
            g2.setColor(Color.BLACK);
            g2.drawRect(x, y, BOX_SIZE, BOX_SIZE);
            String text = String.valueOf(array[i]);
            FontMetrics fm = g2.getFontMetrics();
            int tx = x + (BOX_SIZE - fm.stringWidth(text))/2;
            int ty = y + (BOX_SIZE + fm.getAscent())/2 - 4;
            g2.drawString(text, tx, ty);
        }
    }
}
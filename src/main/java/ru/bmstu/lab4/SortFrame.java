package ru.bmstu.lab4;

import javax.swing.*;

public class SortFrame extends JFrame {
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

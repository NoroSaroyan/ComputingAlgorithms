package ru.bmstu.Lab3;

/**
 * Класс узла односвязного списка.
 * Каждый узел хранит целочисленное значение и ссылку на следующий узел.
 */
public class Node {
    int value;
    Node next;

    /**
     * Создает новый узел с заданным значением.
     *
     * @param value целое число, которое будет храниться в узле.
     */
    public Node(int value) {
        this.value = value;
    }
}
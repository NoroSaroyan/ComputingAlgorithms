package ru.bmstu.Lab7;

import java.util.Scanner;

public class BinaryTreeSolution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Демонстрация работы с бинарными деревьями

        // Создание первого дерева
        System.out.println("\nВведите символы для первого дерева (без пробелов):");
        String sequence1 = scanner.nextLine();
        BinaryTree tree1 = createMinimalHeightTree(sequence1);

        System.out.println("\nПервое дерево:");
        tree1.writeTo();

        // Создание второго дерева
        System.out.println("\nВведите символы для второго дерева (без пробелов):");
        String sequence2 = scanner.nextLine();
        BinaryTree tree2 = createMinimalHeightTree(sequence2);

        System.out.println("\nВторое дерево:");
        tree2.writeTo();

        // Поиск узла в первом дереве
        System.out.println("\nВведите символ, который нужно найти в первом дереве:");
        char targetValue = scanner.nextLine().charAt(0);

        Node foundNode = tree1.searchBottomUp(targetValue);

        if (foundNode != null) {
            System.out.println("Найден узел со значением " + foundNode.value);

            // Подключение второго дерева
            connectTreeAsLeftSubtree(foundNode, tree2);

            System.out.println("\nПервое дерево после подключения второго дерева:");
            tree1.writeTo();
        } else {
            System.out.println("Узел со значением " + targetValue + " не найден в первом дереве.");
        }

        scanner.close();
    }

    // Подключение второго дерева как левого поддерева
    private static void connectTreeAsLeftSubtree(Node node, BinaryTree tree2) {
        if (tree2.root == null) {
            return; // Если второе дерево пустое, ничего не делаем
        }

        if (node.left == null) {
            // Если у найденного узла нет левого поддерева,
            // подключаем второе дерево как его левое поддерево
            node.left = tree2.root;
            tree2.root.father = node;
            System.out.println("Второе дерево подключено как левое поддерево узла " + node.value);
        } else {
            // Ищем крайний левый потомок с пустым левым поддеревом
            Node current = node.left;
            while (current.left != null) {
                current = current.left;
            }

            current.left = tree2.root;
            tree2.root.father = current;
            System.out.println("Второе дерево подключено как левое поддерево крайнего левого потомка узла " + node.value);
        }
    }

    // Создание дерева минимальной высоты из последовательности символов
    private static BinaryTree createMinimalHeightTree(String sequence) {
        BinaryTree tree = new BinaryTree();

        if (sequence == null || sequence.isEmpty()) {
            return tree;
        }

        // Преобразуем строку в массив символов
        char[] chars = sequence.toCharArray();

        // Создаем дерево минимальной высоты рекурсивно
        tree.root = createMinimalHeightTreeHelper(chars, 0, chars.length - 1);

        return tree;
    }

    private static Node createMinimalHeightTreeHelper(char[] chars, int start, int end) {
        if (start > end) {
            return null;
        }

        // Находим середину текущего диапазона
        int mid = (start + end) / 2;

        // Создаем узел из среднего элемента
        Node node = new Node(chars[mid]);

        // Рекурсивно создаем левое и правое поддеревья
        node.left = createMinimalHeightTreeHelper(chars, start, mid - 1);
        if (node.left != null) {
            node.left.father = node;
        }

        node.right = createMinimalHeightTreeHelper(chars, mid + 1, end);
        if (node.right != null) {
            node.right.father = node;
        }

        return node;
    }
}




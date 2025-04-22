package ru.bmstu.Lab7;

// Класс бинарного дерева
public class BinaryTree {
    Node root;

    public BinaryTree() {
        this.root = null;
    }

    // Создание пустого дерева
    public void create() {
        this.root = null;
    }

    // Очистка дерева
    public void clear() {
        this.root = null;
    }

    // Определение числа узлов поддерева
    public int nodesQuantity(Node p) {
        if (p == null) {
            return 0;
        }
        return 1 + nodesQuantity(p.left) + nodesQuantity(p.right);
    }

    // Операция получения адреса узла со значением v
    public Node addr(char v) {
        return addrHelper(root, v);
    }

    private Node addrHelper(Node node, char v) {
        if (node == null) {
            return null;
        }

        if (node.value == v) {
            return node;
        }

        Node leftResult = addrHelper(node.left, v);
        if (leftResult != null) {
            return leftResult;
        }

        return addrHelper(node.right, v);
    }

    // Поиск узла с заданным значением в дереве (обход снизу вверх)
    public Node searchBottomUp(char value) {
        return searchBottomUpHelper(root, value);
    }

    private Node searchBottomUpHelper(Node node, char value) {
        if (node == null) {
            return null;
        }

        Node leftResult = searchBottomUpHelper(node.left, value);
        if (leftResult != null) {
            return leftResult;
        }

        Node rightResult = searchBottomUpHelper(node.right, value);
        if (rightResult != null) {
            return rightResult;
        }

        // Проверка текущего узла (посещаем после детей)
        if (node.value == value) {
            return node;
        }

        return null;
    }

    // Вывод дерева с помощью отступов
    public void writeTo() {
        writeToHelper(root, 0);
    }

    private void writeToHelper(Node node, int level) {
        if (node == null) {
            return;
        }

        // Выводим правое поддерево
        writeToHelper(node.right, level + 1);

        // Выводим текущий узел
        for (int i = 0; i < level; i++) {
            System.out.print("    ");
        }
        System.out.println(node.value);

        // Выводим левое поддерево
        writeToHelper(node.left, level + 1);
    }

    // Обход дерева снизу вверх (postorder)
    public void traverseBottomUp() {
        System.out.print("Обход дерева снизу вверх: ");
        traverseBottomUpHelper(root);
        System.out.println();
    }

    private void traverseBottomUpHelper(Node node) {
        if (node == null) {
            return;
        }

        // Сначала обходим левое поддерево
        traverseBottomUpHelper(node.left);

        // Затем обходим правое поддерево
        traverseBottomUpHelper(node.right);

        // Затем выводим значение текущего узла
        System.out.print(node.value + " ");
    }

    // Обход дерева сверху вниз (preorder)
    public void traverseTopDown() {
        System.out.print("Обход дерева сверху вниз: ");
        traverseTopDownHelper(root);
        System.out.println();
    }

    private void traverseTopDownHelper(Node node) {
        if (node == null) {
            return;
        }

        // Сначала выводим значение текущего узла
        System.out.print(node.value + " ");

        // Затем обходим левое поддерево
        traverseTopDownHelper(node.left);

        // Затем обходим правое поддерево
        traverseTopDownHelper(node.right);
    }

    // Обход дерева слева направо (inorder)
    public void traverseLeftToRight() {
        System.out.print("Обход дерева слева направо: ");
        traverseLeftToRightHelper(root);
        System.out.println();
    }

    private void traverseLeftToRightHelper(Node node) {
        if (node == null) {
            return;
        }

        // Сначала обходим левое поддерево
        traverseLeftToRightHelper(node.left);

        // Затем выводим значение текущего узла
        System.out.print(node.value + " ");

        // Затем обходим правое поддерево
        traverseLeftToRightHelper(node.right);
    }
}
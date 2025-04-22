package ru.bmstu.Lab7;

// Класс узла бинарного дерева
class Node {
    char value;
    Node left;
    Node right;
    Node father;

    public Node(char value) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.father = null;
    }

    // Примитивные операции над узлами бинарного дерева
    public char value() {
        return value;
    }

    public Node left() {
        return left;
    }

    public Node right() {
        return right;
    }

    public Node father() {
        return father;
    }

    public Node brother() {
        if (father == null) {
            return null;
        }
        return isLeft() ? father.right : father.left;
    }

    public boolean isLeft() {
        return father != null && father.left == this;
    }

    public boolean isRight() {
        return father != null && father.right == this;
    }
}
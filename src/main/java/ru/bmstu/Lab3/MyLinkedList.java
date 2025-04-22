package ru.bmstu.Lab3;

/**
 * <h1>
 *     13 ВАРИАНТ
 *     "Вычислительные алгоритмы"(2024-25 уч. год)
 * </h1>
 * <p>
 * Класс демонстрирует базовые операции со списком, такие как добавление элементов,
 * удаление, поиск, обновление значений, а также решения  задач по варианту
 * </p>
 * <p>
 * Здесь вы найдете:
 * <ul>
 *   <li>Общие базовые операции: добавление в начало и конец, подсчет элементов, удаление и вывод.</li>
 *   <li>Методы доступа и обновления: установка элемента по индексу и обновление первого найденного узла по значению.</li>
 *   <li>Специфические задачи:
 *       <ul>
 *         <li>Задача 1: Разворот списка (смена указателей без создания новых узлов).</li>
 *         <li>Задача 2: Вывод элементов с использованием стека (положительные и отрицательные числа в обратном порядке).</li>
 *         <li>Задача 3: Проверка симметрии списка с применением стека и очереди.</li>
 *       </ul>
 *   </li>
 * </ul>
 * </p>
 * <p>
 * Этот класс написан с целью помочь студентам разобраться в работе односвязных списков.
 * Надеюсь, код будет понятен и полезен при изучении основных алгоритмов.
 * </p>
 *
 * @author noriksaroyan@gmail.com (ИУК4-43Б)
 */
public class MyLinkedList {

    /** Ссылка на первый узел списка. */
    private Node head;

    // ------------------------------------------------------------------------
    // Общие базовые операции (добавление, удаление, поиск, изменение, подсчет, вывод)
    // ------------------------------------------------------------------------

    /**
     * Добавляет новый элемент в начало списка.
     *
     * @param value значение, которое нужно добавить.
     */
    public void addFirst(int value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
    }

    /**
     * Добавляет новый элемент в конец списка.
     *
     * @param value значение, которое нужно добавить.
     */
    public void addLast(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
            return;
        }
        Node curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = newNode;
    }

    /**
     * Возвращает общее количество элементов в списке.
     *
     * @return число узлов в списке.
     */
    public int countElements() {
        int count = 0;
        Node curr = head;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        return count;
    }

    /**
     * Удаляет первый элемент списка.
     *
     * @return true, если элемент был успешно удален, или false, если список пуст.
     */
    public boolean removeFirst() {
        if (head == null) {
            return false;
        }
        head = head.next;
        return true;
    }

    /**
     * Удаляет первое вхождение указанного значения из списка.
     *
     * @param value значение, которое нужно удалить.
     * @return true, если удаление прошло успешно, иначе false.
     */
    public boolean remove(int value) {
        if (head == null) {
            return false;
        }
        if (head.value == value) {
            head = head.next;
            return true;
        }
        Node curr = head;
        while (curr.next != null) {
            if (curr.next.value == value) {
                curr.next = curr.next.next;
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    /**
     * Выводит все элементы списка в порядке их следования.
     */
    public void printList() {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.value + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    // ------------------------------------------------------------------------
    // Доступ к элементам и обновление
    // ------------------------------------------------------------------------

    /**
     * Устанавливает новое значение для элемента, расположенного по заданному индексу.
     *
     * @param index   позиция элемента, который нужно обновить.
     * @param element новое значение для элемента.
     * @throws IndexOutOfBoundsException если индекс выходит за границы списка.
     */
    public void set(int index, int element) {
        Node curr = head;
        int currentIndex = 0;
        while (curr != null && currentIndex < index) {
            curr = curr.next;
            currentIndex++;
        }
        if (curr == null) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        curr.value = element;
    }

    /**
     * Обновляет значение первого найденного узла, равного заданному.
     * Обновляется только первое вхождение.
     *
     * @param val    старое значение, которое требуется заменить.
     * @param newVal новое значение, которое будет установлено.
     */
    public void update(int val, int newVal) {
        Node curr = head;
        while (curr != null) {
            if (curr.value == val) {
                curr.value = newVal;
                break;
            }
            curr = curr.next;
        }
    }

    /**
     * Задача 1: Разворот списка.
     * Разворачивает список, изменяя только ссылки между узлами.
     * Этот метод не создает новых узлов, а лишь переназначает указатели, меняя порядок узлов на обратный.
     */
    public void reverse() {
        Node prev = null;
        Node curr = head;
        while (curr != null) {
            Node nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        head = prev;
    }

    /**
     * Задача 2: Вывод элементов списка с использованием стека.
     * Проходит по списку один раз и выводит:
     * <ul>
     *   <li>Положительные числа в обратном порядке их следования</li>
     *   <li>Отрицательные числа в обратном порядке их следования</li>
     * </ul>
     * Элементы, равные 0, не выводятся.
     */
    public void printByStacks() {
        java.util.Stack<Integer> posStack = new java.util.Stack<>();
        java.util.Stack<Integer> negStack = new java.util.Stack<>();

        Node curr = head;
        while (curr != null) {
            if (curr.value > 0) {
                posStack.push(curr.value);
            } else if (curr.value < 0) {
                negStack.push(curr.value);
            }
            curr = curr.next;
        }

        System.out.print("Положительные числа (обратный порядок): ");
        while (!posStack.isEmpty()) {
            System.out.print(posStack.pop() + " ");
        }
        System.out.println();

        System.out.print("Отрицательные числа (обратный порядок): ");
        while (!negStack.isEmpty()) {
            System.out.print(negStack.pop() + " ");
        }
        System.out.println();
    }

    /**
     * Задача 3: Проверка симметрии списка с использованием стека и очереди.
     * Список считается симметричным, если его элементы читаются одинаково в обоих направлениях.
     * Пустой список считается симметричным, а при нечетном количестве элементов центральный узел игнорируется.
     *
     * @return true, если список симметричный, иначе false.
     */
    public boolean isSymmetricStackQueue() {
        if (head == null) return true;
        int count = countElements();
        int half = count / 2;

        java.util.Queue<Integer> queue = new java.util.LinkedList<>();
        java.util.Stack<Integer> stack = new java.util.Stack<>();

        Node curr = head;
        int index = 0;
        while (curr != null) {
            if (index < half) {
                queue.offer(curr.value);
            } else if (count % 2 != 0 && index == half) {
                // Если число элементов нечетное, центральный элемент пропускается
            } else {
                stack.push(curr.value);
            }
            index++;
            curr = curr.next;
        }

        while (!queue.isEmpty() && !stack.isEmpty()) {
            if (!queue.poll().equals(stack.pop())) {
                return false;
            }
        }
        return true;
    }
}

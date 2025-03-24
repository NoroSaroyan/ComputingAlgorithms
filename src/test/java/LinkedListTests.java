

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.bmstu.Lab3.MyLinkedList;

/**
 * Тесты для класса MyLinkedList.
 * <p>
 * Здесь протестированы все основные функции нашего односвязного списка:
 * базовые операции (добавление, удаление, поиск, обновление, подсчет, вывод),
 * а также специфические задачи: разворот списка, вывод с использованием стека и
 * проверка симметрии списка с помощью стека и очереди.
 * </p>
 * <p>
 * Каждый тест снабжен комментариями для лучшего понимания того, что и почему проверяется.
 * </p>
 */
public class LinkedListTests {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        // Перенаправляем вывод в наш буфер для проверки печати
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    /**
     * @AfterEach используем, чтоб после каждого теста восстановить стандартный вывод
     */
    @AfterEach
    public void tearDown() {
        // Восстанавливаем стандартный вывод после каждого теста
        System.setOut(originalOut);
    }

    /**
     * Вспомогательный метод для получения результата работы метода printList.
     *
     * @param list наш список
     * @return строка, которая была выведена на экран методом printList
     */
    private String getPrintedOutput(MyLinkedList list) {
        outputStream.reset();
        list.printList();
        return outputStream.toString();
    }

    // ------------------------------------------------------------------------
    // Тесты для общих базовых операций
    // ------------------------------------------------------------------------

    @Test
    public void testAddFirst() {
        MyLinkedList list = new MyLinkedList();
        // Добавляем элементы в начало списка
        list.addFirst(10);
        list.addFirst(20);
        // Ожидаемый порядок: 20 10
        assertEquals("20 10 \n", getPrintedOutput(list));
    }

    @Test
    public void testAddLast() {
        MyLinkedList list = new MyLinkedList();
        // Добавляем элементы в конец списка
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        // Ожидаем: 10 20 30
        assertEquals("10 20 30 \n", getPrintedOutput(list));
    }

    @Test
    public void testCountElements() {
        MyLinkedList list = new MyLinkedList();
        // Пустой список должен иметь 0 элементов
        assertEquals(0, list.countElements());
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        // После добавления трех элементов ожидаем, что список содержит 3 узла
        assertEquals(3, list.countElements());
    }

    @Test
    public void testRemoveFirst() {
        MyLinkedList list = new MyLinkedList();
        list.addLast(5);
        list.addLast(10);
        list.addLast(15);
        // Удаляем первый элемент (5)
        boolean removed = list.removeFirst();
        assertTrue(removed);
        // Ожидаем, что список теперь: 10 15
        assertEquals("10 15 \n", getPrintedOutput(list));
    }

    @Test
    public void testRemoveValue() {
        MyLinkedList list = new MyLinkedList();
        list.addLast(5);
        list.addLast(10);
        list.addLast(15);
        // Удаляем средний элемент (10)
        boolean removed = list.remove(10);
        assertTrue(removed);
        // Ожидаем список: 5 15
        assertEquals("5 15 \n", getPrintedOutput(list));
        // Проверяем, что при попытке удалить несуществующее значение возвращается false
        assertFalse(list.remove(100));
    }

    @Test
    public void testPrintList() {
        MyLinkedList list = new MyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        // Вывод должен соответствовать "1 2 3"
        String expected = "1 2 3 \n";
        assertEquals(expected, getPrintedOutput(list));
    }

    // ------------------------------------------------------------------------
    // Тесты для операций доступа к элементам и обновления
    // ------------------------------------------------------------------------

    @Test
    public void testSet() {
        MyLinkedList list = new MyLinkedList();
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        // Изменяем элемент с индексом 1 (значение 20) на 200
        list.set(1, 200);
        assertEquals("10 200 30 \n", getPrintedOutput(list));

        // Проверяем, что при попытке обновить элемент по неверному индексу выбрасывается исключение
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> list.set(5, 100));
        assertTrue(exception.getMessage().contains("Index: 5"));
    }

    @Test
    public void testUpdate() {
        MyLinkedList list = new MyLinkedList();
        list.addLast(5);
        list.addLast(10);
        list.addLast(5);
        // Обновляем первое вхождение значения 5 на 50
        list.update(5, 50);
        // Ожидаем: 50 10 5 (только первый узел со значением 5 обновлен)
        assertEquals("50 10 5 \n", getPrintedOutput(list));
    }

    // ------------------------------------------------------------------------
    // Тесты для специфических задач
    // ------------------------------------------------------------------------

    @Test
    public void testReverse() {
        MyLinkedList list = new MyLinkedList();
        // Формируем список: 1 -> 2 -> 3 -> 4 -> 5
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        // Разворачиваем список: ожидаем 5 -> 4 -> 3 -> 2 -> 1
        list.reverse();
        assertEquals("5 4 3 2 1 \n", getPrintedOutput(list));
    }

    @Test
    public void testPrintByStacks_AllPositive() {
        MyLinkedList list = new MyLinkedList();
        // Формируем список только с положительными числами: 1, 2, 3
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        outputStream.reset();
        list.printByStacks();
        String output = outputStream.toString();
        // Ожидаем, что положительные числа выведутся в обратном порядке: 3, 2, 1
        assertTrue(output.contains("Положительные числа (обратный порядок): 3 2 1"));
        // Для отрицательных чисел вывод может быть пустым
        assertTrue(output.contains("Отрицательные числа (обратный порядок):"));
    }

    @Test
    public void testPrintByStacks_MixedValues() {
        MyLinkedList list = new MyLinkedList();
        // Формируем список: -1, 0, 4, -5, 3
        list.addLast(-1);
        list.addLast(0);
        list.addLast(4);
        list.addLast(-5);
        list.addLast(3);
        outputStream.reset();
        list.printByStacks();
        String output = outputStream.toString();
        // Ожидаем, что положительные числа выведутся в обратном порядке: 3, 4
        assertTrue(output.contains("Положительные числа (обратный порядок): 3 4"));
        // Ожидаем, что отрицательные числа выведутся в обратном порядке: -5, -1
        assertTrue(output.contains("Отрицательные числа (обратный порядок): -5 -1"));
    }

    @Test
    public void testIsSymmetricStackQueue_Symmetric() {
        MyLinkedList list = new MyLinkedList();
        // Формируем симметричный список: 1 -> 2 -> 3 -> 2 -> 1
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(2);
        list.addLast(1);
        assertTrue(list.isSymmetricStackQueue(), "Список должен быть симметричным");
    }

    @Test
    public void testIsSymmetricStackQueue_NonSymmetric() {
        MyLinkedList list = new MyLinkedList();
        // Формируем несимметричный список: 1 -> 2 -> 3 -> 4 -> 5
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        assertFalse(list.isSymmetricStackQueue(), "Список не должен быть симметричным");
    }

    @Test
    public void testIsSymmetricStackQueue_SingleElement() {
        MyLinkedList list = new MyLinkedList();
        // Один элемент всегда симметричен
        list.addLast(42);
        assertTrue(list.isSymmetricStackQueue(), "Одиночный элемент всегда симметричен");
    }

    @Test
    public void testIsSymmetricStackQueue_EmptyList() {
        MyLinkedList list = new MyLinkedList();
        // Пустой список по определению симметричен
        assertTrue(list.isSymmetricStackQueue(), "Пустой список считается симметричным");
    }
}

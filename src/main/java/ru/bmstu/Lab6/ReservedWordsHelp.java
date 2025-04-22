package ru.bmstu.Lab6;

import java.util.*;

public class ReservedWordsHelp {
    private static class Entry {
        String word;
        String help;

        Entry(String word, String help) {
            this.word = word;
            this.help = help;
        }
    }

    private static final double LOAD_FACTOR_THRESHOLD = 0.75;
    private Entry[] table;
    private int size = 0;

    public ReservedWordsHelp(int capacity) {
        table = new Entry[capacity];
    }

    private int hash(String key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    private void rehash() {
        System.out.println("Реструктуризация таблицы...");
        Entry[] oldTable = table;
        table = new Entry[oldTable.length * 2];
        size = 0;
        for (Entry e : oldTable) {
            if (e != null) {
                insert(e.word, e.help);
            }
        }
    }

    public void insert(String word, String help) {
        if ((double) size / table.length > LOAD_FACTOR_THRESHOLD) {
            rehash();
        }
        int index = hash(word);
        while (table[index] != null) {
            if (table[index].word.equals(word)) {
                table[index].help = help;
                return;
            }
            index = (index + 1) % table.length;
        }
        table[index] = new Entry(word, help);
        size++;
    }

    public String getHelp(String word) {
        int index = hash(word);
        int start = index;
        while (table[index] != null) {
            if (table[index].word.equals(word)) {
                return table[index].help;
            }
            index = (index + 1) % table.length;
            if (index == start) break;
        }
        return null;
    }

    public void printTable() {
        for (int i = 0; i < table.length; i++) {
            Entry e = table[i];
            if (e != null) {
                System.out.printf("%2d: %-10s -> %s%n", i, e.word, e.help);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReservedWordsHelp hashTable = new ReservedWordsHelp(23);

        // базовые 20+ зарезервированных слов Java
        String[][] base = {
            {"class", "Объявление класса"},
            {"public", "Область видимости"},
            {"static", "Статическая переменная или метод"},
            {"void", "Метод без возвращаемого значения"},
            {"int", "Целочисленный тип"},
            {"double", "Тип с плавающей точкой"},
            {"float", "Тип с плавающей точкой одинарной точности"},
            {"char", "Символьный тип"},
            {"boolean", "Логический тип"},
            {"if", "Условный оператор"},
            {"else", "Альтернативная ветка условия"},
            {"while", "Цикл с предусловием"},
            {"for", "Цикл с параметром"},
            {"break", "Выход из цикла"},
            {"continue", "Переход к следующей итерации цикла"},
            {"new", "Создание нового объекта"},
            {"return", "Возврат значения из метода"},
            {"try", "Начало блока обработки исключений"},
            {"catch", "Блок перехвата исключений"},
            {"finally", "Блок, выполняемый в любом случае"},
            {"import", "Импорт библиотек"}
        };

        for (String[] pair : base) {
            hashTable.insert(pair[0], pair[1]);
        }

        while (true) {
            System.out.print("Введите зарезервированное слово или 'exit': ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) break;

            String help = hashTable.getHelp(input);
            if (help != null) {
                System.out.println("Подсказка: " + help);
            } else {
                System.out.print("Слово не найдено. Введите подсказку для '" + input + "': ");
                String newHelp = scanner.nextLine();
                hashTable.insert(input, newHelp);
                System.out.println("Слово добавлено.");
            }
        }

        System.out.println("\nФинальная таблица:");
        hashTable.printTable();
    }
}

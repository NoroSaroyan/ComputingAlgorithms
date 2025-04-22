package ru.bmstu.Lab1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {

    // Метод для вычисления дня недели по формуле Целлера
    public static int dayOfWeek(int day, int month, int year) {
        // Если месяц январь или февраль, корректируем месяц и год
        if (month < 3) {
            month += 12;
            year -= 1;
        }
        int K = year % 100;
        int J = year / 100;
        int h = (day + (13 * (month + 1)) / 5 + K + (K / 4) + (J / 4) + 5 * J) % 7;
        return h;
    }

    public static String weekdayName(int weekday) {
        return switch (weekday) {
            case 0 -> "Суббота";
            case 1 -> "Воскресенье";
            case 2 -> "Понедельник";
            case 3 -> "Вторник";
            case 4 -> "Среда";
            case 5 -> "Четверг";
            case 6 -> "Пятница";
            default -> "";
        };
    }

    public static void main(String[] args) throws IOException {
        long startTime = System.nanoTime();

        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String date = r.readLine();
        int birthDay = Integer.parseInt(date.split(":")[0]);
        int birthMonth = Integer.parseInt(date.split(":")[1]);
        int birthYear = Integer.parseInt(date.split(":")[2]);

        int birthdayWeekday = dayOfWeek(birthDay, birthMonth, birthYear);
        System.out.println("День недели данной даты (" + birthDay + ":" + birthMonth + ":" + birthYear + "): " + weekdayName(birthdayWeekday));
        System.out.println("Годы в течение столетия, когда день недели совпадает с днём рождения:");

        for (int year = birthYear + 1; year < birthYear + 100; year++) {
            if (dayOfWeek(birthDay, birthMonth, year) == birthdayWeekday) {
                System.out.println(year);
            }
        }

        long endTime = System.nanoTime();
        long durationMs = (endTime - startTime) / 1_000_000;
        System.out.println();
        System.out.println("Время выполнения программы: " + durationMs + " миллисекунд");

    }
}

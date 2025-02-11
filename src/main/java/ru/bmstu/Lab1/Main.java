package ru.bmstu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        switch (weekday) {
            case 0:
                return "Суббота";
            case 1:
                return "Воскресенье";
            case 2:
                return "Понедельник";
            case 3:
                return "Вторник";
            case 4:
                return "Среда";
            case 5:
                return "Четверг";
            case 6:
                return "Пятница";
            default:
                return "";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(
                new InputStreamReader(System.in));
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
    }
}

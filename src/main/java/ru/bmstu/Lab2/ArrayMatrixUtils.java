package ru.bmstu.Lab2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ArrayMatrixUtils {

    /**
     * Находит пересечение двух массивов целых чисел, возвращая результат без дубликатов.
     * Пример: [1, 2, 2, 1] и [2, 2] -> [2]
     *
     * @param nums1 первый массив
     * @param nums2 второй массив
     * @return массив с пересечением элементов
     */
    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }

        Set<Integer> resultSet = new HashSet<>();
        for (int num : nums2) {
            if (set1.contains(num)) {
                resultSet.add(num);
            }
        }

        int[] result = new int[resultSet.size()];
        int i = 0;
        for (Integer num : resultSet) {
            result[i++] = num;
        }
        return result;
    }

    /**
     * Транспонирует заданную матрицу.
     * Транспонирование подразумевает замену строк на столбцы.
     *
     * @param matrix исходная матрица
     * @return транспонированная матрица
     */
    public static int[][] transpose(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0][];
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] transposed = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed[j][i] = matrix[i][j];
            }
        }

        return transposed;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 2, 3, 4};
        int[] arr2 = new int[]{4, 2, 1, 3};
        System.out.println(Arrays.toString(intersection(arr1, arr2)));
        int[][] matrix3 =
                {{3, 2, 1, 7},
                {9, 11, 5, 4},
                {6, 0, 13, 17},
                {7, 21, 14, 15}};
        matrix3 = transpose(matrix3);
        for (int i = 0; i < matrix3.length; i++) {
            for (int j = 0; j < matrix3.length; j++) {
                System.out.print(matrix3[i][j] + " ");
            }
            System.out.println("\n");
        }
    }
}

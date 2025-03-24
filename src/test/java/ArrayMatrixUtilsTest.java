import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import ru.bmstu.Lab2.ArrayMatrixUtils;

import java.util.Arrays;

public class ArrayMatrixUtilsTest {

    @Test
    public void testIntersectionWithDuplicates() {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        int[] expected = {2};
        int[] result = ArrayMatrixUtils.intersection(nums1, nums2);

        Arrays.sort(result);
        Arrays.sort(expected);
        assertArrayEquals(expected, result, "Пересечение массивов должно вернуть [2]");
    }

    @Test
    public void testIntersectionNoCommonElements() {
        int[] nums1 = {1, 2, 3};
        int[] nums2 = {4, 5, 6};
        int[] expected = {};
        int[] result = ArrayMatrixUtils.intersection(nums1, nums2);
        Arrays.sort(result);
        Arrays.sort(expected);
        assertArrayEquals(expected, result, "Пересечение массивов без общих элементов должно вернуть пустой массив");
    }

    @Test
    public void testTransposeRectangularMatrix() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6}
        };
        int[][] expected = {
                {1, 4},
                {2, 5},
                {3, 6}
        };
        int[][] result = ArrayMatrixUtils.transpose(matrix);
        assertTrue(Arrays.deepEquals(expected, result), "Транспонированная матрица не соответствует ожидаемому результату.");
    }

    @Test
    public void testTransposeSingleRowMatrix() {
        int[][] matrix = {
                {1, 2, 3}
        };
        int[][] expected = {
                {1},
                {2},
                {3}
        };
        int[][] result = ArrayMatrixUtils.transpose(matrix);
        assertTrue(Arrays.deepEquals(expected, result), "Транспонированная матрица для матрицы с одной строкой не соответствует ожидаемому результату.");
    }
}

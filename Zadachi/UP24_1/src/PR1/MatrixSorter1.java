package PR1;

import java.util.Arrays;
public class MatrixSorter1 {
    public static void main(String[] args) {
        int[][] matrix = {
                {3, 2, 1},
                {1, 5, 3},
                {4, 4, 2},
                {2, 2, 2}
        };
        System.out.println("Исходная матрица:");
        printMatrix(matrix);
        sortMatrixByRowSum(matrix);
        System.out.println("Отсортированная матрица:");
        printMatrix(matrix);
    }
    public static void sortMatrixByRowSum(int[][] matrix) {
        Arrays.sort(matrix, (row1, row2) -> {
            int sum1 = Arrays.stream(row1).sum();
            int sum2 = Arrays.stream(row2).sum();
            return Integer.compare(sum1, sum2);
        });
    }
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}

import java.util.HashMap;
import java.util.Random;

public class TimeMatrix implements Rotateable, Transposable{
    public int[][] generateMatrix(int n) {

        int[][] matrix = new int[n][n];
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        shuffleArray(numbers);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = numbers[i * matrix[i].length + j];
            }
        }
        return matrix;
    }

    @Override
    public void rotate(int[][] matrix, int degrees) {
        if (degrees % 90 != 0) {
            throw new IllegalArgumentException("Degrees must be multiple of 90");
        }
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = i; j < n - i - 1; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }

    @Override
    public void transpose(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[i].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    public HashMap<Integer, Integer> matrixToHashMap(int[][] matrix) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                map.put(i * matrix.length + j, matrix[i][j]);
            }
        }
        return map;
    }


    public void setPlayerPos(int[][] matrix, int[] pos) {
        Random rand = new Random();
        pos[0] = rand.nextInt(matrix.length);
        pos[1] = rand.nextInt(matrix[0].length);
        matrix[pos[0]][pos[1]] += 9;
    }

    public void moveLeft(int[][] matrix, int[] pos) {
        if (pos[1] == 0) {
            pos[1] = matrix[0].length - 1;
        } else {
            pos[1]--;
        }
        matrix[pos[0]][pos[1]] += 9;
    }

    public void moveRight(int[][] matrix, int[] pos) {
        if (pos[1] == matrix[0].length - 1) {
            pos[1] = 0;
        } else {
            pos[1]++;
        }
        matrix[pos[0]][pos[1]] += 9;
    }

    public void moveUp(int[][] matrix, int[] pos) {
        if (pos[0] == 0) {
            pos[0] = matrix.length - 1;
        } else {
            pos[0]--;
        }
        matrix[pos[0]][pos[1]] += 9;
    }

    public void moveDown(int[][] matrix, int[] pos) {
        if (pos[0] == matrix.length - 1) {
            pos[0] = 0;
        } else {
            pos[0]++;
        }
        matrix[pos[0]][pos[1]] += 9;
    }

    private void shuffleArray(int[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

}

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;

public class TimeMatrixTest {

    @Test
    public void generateMatrixTest() {
        TimeMatrix tm = new TimeMatrix();
        int n = 3;
        int[][] matrix = tm.generateMatrix(n);

        assertEquals(n, matrix.length);
        for (int i = 0; i < matrix.length; i++) {
            assertEquals(n, matrix[i].length);
        }

        int[] expected = new int[n*n];
        for (int i = 0; i < expected.length; i++) {
            expected[i] = i+1;
        }
        int[] matrixElements = new int[n*n];
        int k = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrixElements[k++] = matrix[i][j];
            }
        }
        Arrays.sort(matrixElements);
        assertArrayEquals(expected, matrixElements);
    }
}


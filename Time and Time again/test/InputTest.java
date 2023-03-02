import org.junit.Test;
import static org.junit.Assert.*;
import java.io.*;

public class InputTest {

    @Test
    public void readFileTest() throws IOException {
        String filename = "Files/1.txt";
        String[] expectedOutput = {"You are in room 1"};

        File testFile = new File(filename);
        testFile.createNewFile();
        FileWriter writer = new FileWriter(testFile);
        for (String line : expectedOutput) {
            writer.write(line + "\n");
        }
        writer.close();

        Input input = new Input();
        String[] output = input.readFile(filename);

        assertArrayEquals(expectedOutput, output);

        testFile.delete();
    }
}


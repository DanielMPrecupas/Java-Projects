import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Input {
    public String[] readFile(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            ArrayList<String> sentences = new ArrayList<String>();
            while ((line = reader.readLine()) != null) {
                sentences.add(line);
            }
            reader.close();
            return sentences.toArray(new String[sentences.size()]);
        } catch (IOException e) {
            e.printStackTrace();
            return new String[]{};
        }
    }
}

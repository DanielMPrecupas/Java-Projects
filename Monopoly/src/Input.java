import java.util.*;
import java.io.*;

public class Input {
    public String[] StringArray(String x)
    {
        String[] arr= null;
        List<String> itemsSchool = new ArrayList<String>();

        try
        {
            FileInputStream f = new FileInputStream(x);
            DataInputStream data_input = new DataInputStream(f);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(data_input));
            String str_line;

            while ((str_line = buffer.readLine()) != null)
            {
                str_line = str_line.trim();
                if ((str_line.length()!=0))
                {
                    itemsSchool.add(str_line);
                }
            }

            arr = (String[])itemsSchool.toArray(new String[itemsSchool.size()]);
        }
        catch (Exception e)
        {
            System.err.println("Error: " + e.getMessage());
        }
        return arr;
    }

    public int RandomNumber(int x, int y){
        Random r = new Random();
        return r.nextInt(x,y+1);
    }

}
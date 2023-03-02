import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Application game = new Application();
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        game.Game(x, y);

    }
}
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Timer;
import java.util.concurrent.TimeUnit.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.sound.sampled.LineUnavailableException;

public class GameLoop {
    public static void createWindow(int n) {
        JFrame frame = new JFrame("Time and Time Again");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Input inp = new Input();
        TimeMatrix matrix = new TimeMatrix();
        int[] pos = {0, 0};
        int[][] mat = matrix.generateMatrix(n);
        matrix.setPlayerPos(mat, pos);

        String fileName = "Files/" + (mat[pos[0]][pos[1]] - 9) + ".txt";

        Random rand = new Random();
        matrix.matrixToHashMap(mat);


        JPanel scene1 = createScene(fileName);
        frame.add(scene1);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_W) {
                    frame.remove(scene1);

                    matrix.moveUp(mat, pos);
                    String filename;
                    filename = "Files/" + (mat[pos[0]][pos[1]] - 9) + ".txt";
                    JPanel scene2 = createScene(filename);

                    frame.add(scene2);
                    frame.revalidate();
                    frame.repaint();
                } else if (key == KeyEvent.VK_A) {
                    frame.remove(scene1);

                    matrix.moveLeft(mat, pos);
                    String filename;
                    filename = "Files/" + (mat[pos[0]][pos[1]] - 9) + ".txt";
                    JPanel scene2 = createScene(filename);

                    frame.add(scene2);
                    frame.revalidate();
                    frame.repaint();
                } else if (key == KeyEvent.VK_S) {
                    frame.remove(scene1);

                    matrix.moveDown(mat, pos);
                    String filename;
                    filename = "Files/" + (mat[pos[0]][pos[1]] - 9) + ".txt";
                    JPanel scene2 = createScene(filename);

                    frame.add(scene2);
                    frame.revalidate();
                    frame.repaint();
                } else if (key == KeyEvent.VK_D) {
                    frame.remove(scene1);
                    matrix.moveRight(mat, pos);
                    String filename;
                    filename = "Files/" + (mat[pos[0]][pos[1]] - 9) + ".txt";
                    JPanel scene2 = createScene(filename);

                    frame.add(scene2);
                    frame.revalidate();
                    frame.repaint();
                }
            }
        });

        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    private static JPanel createScene(String filename) {
        JPanel panel = new JPanel();
        JLabel textLabel = new JLabel("", SwingConstants.CENTER);
        textLabel.setFont(new Font("C&C Red Alert [INET]", Font.PLAIN, 20));
        textLabel.setPreferredSize(new Dimension(1000,500));
        panel.add(textLabel);

        Input inp = new Input();
        Effects eff = new Effects();

        String[] sentences = inp.readFile(filename);
        final int[] currentSentence = {0};

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (currentSentence[0] < sentences.length) {
                    SwingUtilities.invokeLater(() -> {
                        textLabel.setText("");
                        eff.animateText(textLabel, sentences[currentSentence[0]]);
                        currentSentence[0]++;
                    });
                } else {
                    timer.cancel();
                }
            }
        }, 0, 3000);

        return panel;
    }

}


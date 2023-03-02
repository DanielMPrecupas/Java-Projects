import javax.swing.*;

public class Effects {
    public void animateText(JLabel label, String text) {
        new Thread(() -> {
            for (int i = 0; i < text.length(); i++) {
                final int j = i;
                try {
                    SwingUtilities.invokeLater(() -> {
                        label.setText(text.substring(0, j + 1));
                    });
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

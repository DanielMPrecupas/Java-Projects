import org.junit.Test;
import javax.swing.*;
import static org.junit.Assert.*;

public class EffectsTest {

    @Test
    public void animateTextTest() {
        JLabel label = new JLabel();
        Effects effects = new Effects();
        effects.animateText(label, "This is a test.");

        assertEquals("T", label.getText());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
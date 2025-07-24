import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            Font font = new Font("Segoe UI", Font.PLAIN, 13);
            UIManager.put("Label.font", font);
            UIManager.put("Button.font", font);
            UIManager.put("TextField.font", font);
        } catch (Exception e) {
            e.printStackTrace();
        }
        javax.swing.SwingUtilities.invokeLater(Start_Window::new);
    }
}
package GUI;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class BaseWindow extends JFrame {
    public BaseWindow() {
        setAppIcon();
    }
    private void setAppIcon() {
        URL iconURL = getClass().getResource("/Assets/icon.png");
        if (iconURL != null) {
            setIconImage(Toolkit.getDefaultToolkit().getImage(iconURL));
        } else {
            System.err.println("Icon not found");
        }
    }
}

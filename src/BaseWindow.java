import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class BaseWindow extends JFrame {
    public BaseWindow() {
        setAppIcon();
    }
    private void setAppIcon() {
        URL iconURL = getClass().getResource("/Images/icon.png");
        if (iconURL != null) {
            setIconImage(Toolkit.getDefaultToolkit().getImage(iconURL));
        } else {
            System.err.println("Icon not found");
        }
    }
}

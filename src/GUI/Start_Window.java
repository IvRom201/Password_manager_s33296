package GUI;

import Core.Database;
import Core.Loader;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Start_Window extends BaseWindow {
    private JLabel status;
    private File select;

    public Start_Window() {
        setTitle("Password Vault");
        setSize(420, 110);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JButton open = new JButton("Open file");
        JButton create = new JButton("Create file");

        open.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                select = chooser.getSelectedFile();
                status.setText(select.getName());
                String pass = JOptionPane.showInputDialog(this, "Enter master password");
                if (pass != null && !pass.isEmpty()) {
                    openDatabase(pass);
                }
            }
        });
        create.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                select = chooser.getSelectedFile();
                status.setText(select.getName());
                String pass = JOptionPane.showInputDialog(this, "Enter master password");
                if (pass != null && !pass.isEmpty()) {
                    Database db = new Database();
                    Loader.writeFile(db, pass, select);
                    new Main_Window(db, select, pass);
                    dispose();
                }
            }
        });

        JPanel butPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        butPanel.add(open);
        butPanel.add(create);

        status = new JLabel("Please select a file", SwingConstants.CENTER);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(butPanel, BorderLayout.CENTER);
        panel.add(status, BorderLayout.PAGE_END);

        add(panel);
        setVisible(true);
    }

    public void openDatabase(String pass) {
        if(select == null  || !select.exists()) {
            status.setText("File not found");
            return;
        }
        Database db = Loader.readFile(select, pass);
        if(db == null || db.getEntries() == null) {
            status.setText("File not found");
            return;
        }
        new Main_Window(db, select, pass);
        dispose();
    }
}
